package yarden_perets_214816407;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Exam implements Examable {
	private String date;
	protected /*LinkedHashSet<Question>*/QuestionManager questions; 
	private boolean displaySolution;
	public static int maxQuestionCapacity = 10;
	protected int maxNumQue;
	
	public static final int MIN_ANSWERS_PER_QUESTION = 4;

	/**
	 * C'tor saves the date and time the test was created
	 * 
	 * @param maxNumQue max number of question in test
	 * @throws NumOfQuestionsException
	 */
	public Exam(int maxNumQue) throws NumOfQuestionsException {
		if (maxNumQue > maxQuestionCapacity)
			throw new NumOfQuestionsException(maxNumQue);

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
	public boolean addQuestion(Question queToAdd) throws NumOfQuestionsException {
		if (questions.getNumQuestions() >= maxNumQue) {
			throw new NumOfQuestionsException(questions.getNumQuestions() + 1, maxNumQue);
			// System.out.println("Error! No more question could be added, reached full
			// capacity");
		}

		return questions.addQuestion(queToAdd);
	}

	public boolean addQuestion(MultiSelectQuestion queToAdd, DefualtAnswers defaults)
			throws NumOfAnswersException, NumOfQuestionsException {
		int numCorrect = queToAdd.getNumCorrect();

		int numOfAns = queToAdd.getNumAnswers();
		if (numOfAns < MIN_ANSWERS_PER_QUESTION)
			throw new NumOfAnswersException(numOfAns);

		queToAdd.addAnswer(defaults.getNoneCorrect((numCorrect == 0)));
		queToAdd.addAnswer(defaults.getMoreThenOneCorrect((numCorrect > 1)));
		return addQuestion((Question) queToAdd);
	}

	/**
	 * @return object to string
	 */
	public String toString() {
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
	public String writeExam(boolean displaySolution) throws FileNotFoundException {
		String filePath = (displaySolution ? "solution_" : "exam_") + date + ".txt";
		File file = new File(filePath);
		PrintWriter pw = new PrintWriter(file);
		this.displaySolution = displaySolution;
		pw.write(this.toString());
		pw.close();
		return filePath;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		return maxNumQue == other.maxNumQue && Objects.equals(questions, other.questions);
	}
	
	@Override
	public void createExam(Repo repo) {
		
		for (int i = 0; i < maxNumQue; i++) {
			Question que = getQuestion(repo);
			if(que != null) {
				
				if(que instanceof MultiSelectQuestion)
					this.addQuestion((MultiSelectQuestion)que, repo);
				else
					this.addQuestion(que);
			}
			
			if(que == null || questions.getNumQuestions() != i+1) 
			{
				i--;// didn't find question or duplicate
			}
		}
	}

}
