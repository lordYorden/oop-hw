package yarden_perets_214816407;

import java.util.Arrays;

//import java.util.Arrays;

//import java.util.Scanner;

public class Repo {

	private String[] answers;
	private Question[] questions;
	private int numQuestions;
	private int numAnswers;

	/**
	 * C'tor
	 * 
	 * Creates an answer array with the 2 defualt answers in the start
	 */
	public Repo() {
		this.answers = new String[2];
		this.questions = null;
		this.numQuestions = 0;
		this.numAnswers = 0;
		addAnswer("No answer is correct"); // answers[0]
		addAnswer("More then one answer is correct"); // answers[1]
	}

	/**
	 * Adds the answer to the array resizes it if needed and checks if the answer
	 * exists
	 * 
	 * @param ansToAdd the answer to add
	 * @return whether the question was added
	 */
	public boolean addAnswer(String ansToAdd) {
		if (doseAnswerExist(ansToAdd)) {
			return false; //dose exist
		}

		if (numAnswers >= answers.length) {
			resizeAnswers(answers.length + 1);
		}

		answers[numAnswers++] = ansToAdd;
		return true;
	}

	/**
	 * Adds the question to the array resizes it if needed
	 * 
	 * @param queToAdd the question to add
	 * @return whether the answer was added
	 */
	public boolean addQuestion(Question queToAdd) {
		if (queToAdd == null)
			return false;

		if (questions == null) {
			this.questions = new Question[1];
		}

		if (numQuestions >= questions.length) {
			resizeQuestions(questions.length + 1);
		}

		questions[numQuestions++] = queToAdd;

		if(queToAdd instanceof MultiSelectQuestion) {
			MultiSelectQuestion multiQ = (MultiSelectQuestion) queToAdd;
			Answer[] answers = multiQ.getAnswers();
			int numAnswers = multiQ.getNumAnswers();

			for (int i = 0; i < numAnswers; i++) {
				this.addAnswer(answers[i].getText());
			}
		}else if (queToAdd instanceof OpenEndedQuestion) {
			this.addAnswer(((OpenEndedQuestion)queToAdd).getSolution());
		}
		
		return true;
	}

	/**
	 * Checks if the index entered is valid then return null/The question based on
	 * it
	 * 
	 * @param id The question id
	 * @return The question object
	 */
	public Question getQuestionByID(int id) {
		for(Question curr: questions) {
			if(curr.getId() == id)
				return curr;
		}
		return null;
	}

	/**
	 * Checks if the index entered is valid then return null/The question based on
	 * it
	 * 
	 * @param index The answer index
	 * @return The answer object
	 */
	public String getAnswerByIndex(int index) {
		if (answers != null && (index < numAnswers && index >= 2)) { // 2 because of default answers (index 0,1)
			return answers[index];
		}
		return null;
	}

	/**
	 * delete the question in the array by replacing it with the end and nullifying
	 * it
	 * 
	 * @param id the question to remove id
	 * @return whether the question was removed
	 */
	public boolean deleteQuestionById(int id) {
		
		for(int i = 0; i < numQuestions; i++) {
			if(questions[i].getId() == id) {
				questions[i] = questions[--numQuestions];
				questions[numQuestions] = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * @return object values
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (numQuestions == 0 || questions == null)
			return "There are no question in the repo!\n";

		builder.append("Questions in the repo: \n\n");
		//builder.append(Arrays.toString(questions));
		for (int i = 0; i < numQuestions; i++) {
//			builder.append(i + 1);
//			builder.append(". ");
			questions[i].setDisplaySolution(false);
			builder.append(questions[i].toString());
		}
		return builder.toString();
	}

	/**
	 * @return number of questions in the repo
	 */
	public int getNumQuestions() {
		return numQuestions;
	}

	/**
	 * @return object values
	 */
	public String toStringAnswers() {
		StringBuilder builder = new StringBuilder();

		if (numAnswers == 0 || answers == null)
			return "There are no answers in the repo!\n";

		builder.append("Answers in the repo: \n");
		for (int i = 0; i < numAnswers; i++) {
			builder.append(i + 1);
			builder.append(". ");
			builder.append(answers[i]);
			builder.append("\n");
		}
		return builder.toString();
	}


	/**
	 * Private helper method resizes the array of answers based on user input
	 * 
	 * @param size new size
	 */
	private void resizeAnswers(int size) {
		String[] newAnswers = new String[size];
		for (int i = 0; i < answers.length; i++) {
			newAnswers[i] = answers[i];
		}

		this.answers = newAnswers;
	}

	/**
	 * Private helper method resizes the array of questions based on user input
	 * 
	 * @param size new size
	 */
	private void resizeQuestions(int size) {
		Question[] newQuestions = new Question[size];
		for (int i = 0; i < questions.length; i++) {
			newQuestions[i] = questions[i];
		}

		this.questions = newQuestions;
	}

	/**
	 * Private helper method Searches the answers array for a muching answer to the
	 * one given
	 * 
	 * @param answer The answer to search for
	 * @return whether it was found
	 */
	private boolean doseAnswerExist(String answer) {
		for (int i = 0; i < numAnswers; i++) {
			if (answers[i].equals(answer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Public helper method Genrates new answers objects of the defualt answers
	 * based of question data provided
	 * 
	 * @param noneCorrect        The boolean field of first defualt answers
	 * @param moreThenOneCorrect The boolean field of second defualt answers
	 * @return Both answers objects
	 */
	public Answer[] generateDefaultAnswers(boolean noneCorrect, boolean moreThenOneCorrect) {
		return new Answer[] { new Answer(answers[0], noneCorrect), new Answer(answers[1], moreThenOneCorrect) };
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Repo))
			return false;
		
		Repo other = (Repo) obj;
		
		if(this.numAnswers != other.numAnswers || this.numQuestions != other.numQuestions)
			return false;
		return Arrays.equals(this.answers, other.answers) && Arrays.equals(this.questions, other.questions);
	}

}
