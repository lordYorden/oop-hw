package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

public class MultiSelectQuestion extends Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = Repo.REPO_VERSION;
	private ElementManager<Answer> answers;
	private int numCorrect;
	public static final int MAX_ANSWERS_CAPACITY = 10;

	/**
	 * C'tor
	 * 
	 * @param text the question itself
	 */
	MultiSelectQuestion(String text, Difficulty difficulty) {
		super(text, difficulty);
		this.numCorrect = 0;
		this.answers = new AnswerManager();
	}

	/**
	 * copy c'tor
	 * 
	 * @param other object to copy
	 */
	MultiSelectQuestion(MultiSelectQuestion other) {
		super(other.text, other.difficulty);
		this.numCorrect = other.numCorrect;
		this.answers = new AnswerManager(other.answers);
	}

	//for hard coded questions
	public MultiSelectQuestion(String text, Difficulty difficulty, Answer[] answers) {
		this(text, difficulty);
		//count correct
		for (Answer answer : answers) {
			addAnswer(answer);
		}
	}
	
	public ElementManager<Answer> getAnswers() {
		return answers;
	}

	public void clear() {
		this.answers.clear();
		this.numCorrect = 0;
	}

	/**
	 * @return the number of correct answers
	 */
	public int getNumCorrect() {
		return numCorrect;
	}

	/**
	 * @return object values
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(answers.toString());

//		int i = 1;
//		for (Answer answer : answers) {
//			builder.append(i);//for a nice print
//			builder.append(". ");
//			builder.append(answer.toString());
//			i++;
//		}
		builder.append("\n");
		return builder.toString();
	}

	/**
	 * adds and answer to the question's possible answers
	 * 
	 * @param ansToAdd answer given
	 * @return whether the answer was added
	 */
	public boolean addAnswer(Answer ansToAdd) {
		
		if (answers.size() >= MAX_ANSWERS_CAPACITY) {
			return false;// array full
		}
		
		//ansToAdd.setId(answers.size() + 1);//for a nice print
		if (ansToAdd.isCorrect()) {
			numCorrect++;
		}
		
		return answers.addElement(ansToAdd);
	}

	public static void deleteAnswersFromAQuestion(MultiSelectQuestion multiQue, Scanner input){
		ElementManager<Answer> answers = multiQue.getAnswers();
		boolean answerExist = true;
		int selection = 0;
		
		if (answers.size() == 0) {
			System.out.println("Error! No answers to remove!");
			return;
		}
		
		do {
			Iterator<Answer> it = multiQue.getAnswers().iterator();
			
			while(it.hasNext()) {
				Answer ans = it.next();
				//ans.setDisplaySolution(false);
				System.out.printf("ID: %d\n%s\n",ans.getId(), ans);
			}
			
			System.out.println("Select Answers to remove (-1 to continue): ");
			selection = input.nextInt();
			input.nextLine();
			
			if (selection != -1)
				answerExist = multiQue.getAnswers().deleteElement(selection);

			if (!answerExist)
				System.out.println("Error! Answer dosen't exist!");

		} while (selection != -1 || !answerExist);
		
		if(answers.size() < Exam.MIN_ANSWERS_PER_QUESTION) {
			throw new NumOfAnswersException(answers.size());
		}
	}

	@Override
	public String getSolution() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.getSolution());
		builder.append(answers.getSolution());
//		builder.append("\n");
//
//		int i = 1;
//		for (Solutionable answer : answers) {
//			builder.append(i);//for a nice print
//			builder.append(". ");
//			builder.append(answer.getSolution());
//			i++;
//		}
		builder.append("\n");
		return builder.toString();
	}

}
