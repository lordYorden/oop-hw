package yarden_perets_214816407;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class hw3_Exam implements Examable {
	private String date;
	protected ElementManager<Question> questions; 
	private boolean displaySolution;
	public static final int MAX_QUESTION_CAPACITY = 10;
	protected int maxNumQue;
	//public static final int MIN_ANSWERS_PER_QUESTION = 4;

	/**
	 * C'tor saves the date and time the test was created
	 * 
	 * @param maxNumQue max number of question in test
	 * @throws NumOfQuestionsException
	 */
	public hw3_Exam(int maxNumQue, int numQuestion) throws NumOfQuestionsException {
		if (maxNumQue > MAX_QUESTION_CAPACITY)
			throw new NumOfQuestionsException(maxNumQue);
		else if(maxNumQue > numQuestion)
			throw new NumOfQuestionsException(maxNumQue, numQuestion);

		questions = new QuestionManager();
		this.maxNumQue = maxNumQue;
		displaySolution = false;
		this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm"));
	}

	/**
	 * Adds a question to the test
	 * 
	 * @param queToAdd question given
	 * @return whether the question was added
	 * @throws NumOfQuestionsException
	 */
	public boolean hw3_addQuestion(Question queToAdd) throws NumOfQuestionsException {
		if (questions.size() >= maxNumQue) {
			throw new NumOfQuestionsException(questions.size() + 1, maxNumQue);
			// System.out.println("Error! No more question could be added, reached full
			// capacity");
		}

		return questions.addElement(queToAdd);
	}

	public boolean hw3_addQuestion(MultiSelectQuestion queToAdd, DefualtAnswers defaults)
			throws NumOfAnswersException, NumOfQuestionsException {
		int numCorrect = queToAdd.getNumCorrect();

		int numOfAns = queToAdd.getAnswers().size();
		if (numOfAns < ExamMakerFacade.MIN_ANSWERS_PER_QUESTION)
			throw new NumOfAnswersException(numOfAns);

		queToAdd.addAnswer(defaults.getNoneCorrect((numCorrect == 0)));
		queToAdd.addAnswer(defaults.getMoreThenOneCorrect((numCorrect > 1)));
		return hw3_addQuestion((Question) queToAdd);
	}

	/**
	 * @return object to string
	 */
	public String hw3_toString() {
		StringBuilder builder = new StringBuilder();	
		if(displaySolution)
			builder.append(questions.getSolution());
		else
			builder.append(questions.toString());	
		return builder.toString();
	}

	/**
	 * writes the to string to a file in the specified format
	 * (exam/solution_yyyy_MM_dd_HH_mm)
	 * 
	 * @param displaySolution exam/solution
	 * @throws FileNotFoundException
	 */
	public String hw3_writeExam(boolean displaySolution) throws FileNotFoundException {
		String filePath = (displaySolution ? "solution_" : "exam_") + date + ".txt";
		File file = new File(filePath);
		PrintWriter pw = new PrintWriter(file);
		this.displaySolution = displaySolution;
		pw.write(hw3_toString());
		pw.close();
		return filePath;
	}

	//@Override
	public boolean hw3_equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		return maxNumQue == other.maxNumQue && Objects.equals(questions, other.questions);
	}
	
	//@Override
	public void hw3_createExam(Repo repo) {
		
		for (int i = 0; i < maxNumQue; i++) {
			Question que = getQuestion(repo.getQuestions(), repo.getAnswers());
			if(que != null) {
				
				if(que instanceof MultiSelectQuestion)
					this.hw3_addQuestion((MultiSelectQuestion)que, repo);
				else
					this.hw3_addQuestion(que);
			}
			
			if(que == null || questions.size() != i+1) 
			{
				i--;// didn't find question or duplicate
			}
		}
	}

}
