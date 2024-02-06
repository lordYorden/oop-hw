package yarden_perets_214816407;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

//import java.util.Arrays;

//import java.util.Scanner;

public class Repo implements Serializable {

	public enum Subject {
		Math, Science, History, Geography
	}

	private LinkedHashSet<Answer> answers;
	private ArrayList<Question> questions;
	private Subject subject;

	/**
	 * C'tor
	 * 
	 * Creates an answer array with the 2 default answers in the start
	 */
	public Repo(Subject subject) {
		this.answers = new LinkedHashSet<>();
		this.questions = new ArrayList<>();
		this.subject = subject;
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
		Answer newAns = new Answer(ansToAdd, false);
		newAns.setId(answers.size());
		return answers.add(newAns);
	}

	/**
	 * Adds the question to the array resizes it if needed
	 * 
	 * @param queToAdd the question to add
	 * @return whether the answer was added
	 */
	public boolean addQuestion(Question queToAdd) { 
		return questions.add(queToAdd);
	}

	/**
	 * Checks if the index entered is valid then return null/The question based on
	 * it
	 * 
	 * @param id The question id
	 * @return The question object
	 */
	public Question getQuestionByID(int id) {
		for (Question curr : questions) {
			if (curr.getId() == id)
				return curr;
		}
		return null;
	}

	/**
	 * @return the numAnswers
	 */
	public int getNumAnswers() {
		return answers.size();
	}

	/**
	 * Checks if the index entered is valid then return null/The question based on
	 * it
	 * 
	 * @param index The answer index
	 * @return The answer object
	 */
	//TODO: update comment
	public Answer getAnswerById(int id) {		
		for(Answer answer : answers) {
			if(answer.getId() == id)
				return answer;
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
		if(questions.isEmpty())
			return false;
		return questions.remove(getQuestionByID(id));
	}

	/**
	 * @return object values
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (questions.isEmpty())
			return "There are no question in the repo!\n";

		builder.append("Subject: ");
		builder.append(subject.name());
		builder.append("\n");
		builder.append("Questions in the repo:\n\n");
		
		for(Question question : questions) {
			question.setDisplaySolution(false);
			builder.append(question.toString());
		}
		return builder.toString();
	}

	/**
	 * @return number of questions in the repo
	 */
	public int getNumQuestions() {
		return questions.size();
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return object values
	 */
	public String toStringAnswers() {
		StringBuilder builder = new StringBuilder();

		if (answers.isEmpty())
			return "There are no answers in the repo!\n";

		builder.append("Answers in the repo: \n");
		for(Answer ans : answers) {
			builder.append(ans.getId());
			builder.append(". ");
			ans.setDisplaySolution(false);
			builder.append(ans);
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * Private helper method resizes the array of answers based on user input
	 * 
	 * @param size new size
	 */
	@Deprecated
	private void resizeAnswers(int size) {
//		String[] newAnswers = new String[size];
//		for (int i = 0; i < answers.length; i++) {
//			newAnswers[i] = answers[i];
//		}
//
//		this.answers = newAnswers;
	}

	/**
	 * Private helper method resizes the array of questions based on user input
	 * 
	 * @param size new size
	 */
	@Deprecated
	private void resizeQuestions(int size) {
//		Question[] newQuestions = new Question[size];
//		for (int i = 0; i < questions.length; i++) {
//			newQuestions[i] = questions[i];
//		}
//
//		this.questions = newQuestions;
	}

	/**
	 * Private helper method Searches the answers array for a muching answer to the
	 * one given
	 * 
	 * @param answer The answer to search for
	 * @return whether it was found
	 */
	@Deprecated
	private boolean doseAnswerExist(String answer) {
//		for (int i = 0; i < numAnswers; i++) {
//			if (answers[i].equals(answer)) {
//				return true;
//			}
//		}
//		return false;
		return answers.contains(answer);
	}

	/**
	 * Ask the user to select an answer from the repo and retrives it
	 * 
	 * @param repo the program's repository
	 * @return the question that was selected
	 */
	public static Question selectQuestionFromRepo(Repo repo, Scanner input) {
		// Scanner input = new Scanner(System.in);
		Question que = null;
		int selection = 0;

		do {
			System.out.print(repo);
			System.out.println("Select a question: ");
			selection = input.nextInt();
			input.nextLine();
			que = repo.getQuestionByID(selection);

			if (que == null)
				System.out.println("Error! Question dosen't exist!");

		} while (que == null);

		return que;
	}

	/**
	 * Ask the user to select a question from the repo and retrives it
	 * 
	 * @param repo the program's repository
	 * @return the answers that was selected
	 */
	public static String selectAnswerFromRepo(Repo repo, Scanner input) {
		// Scanner input = new Scanner(System.in);
		Answer ans = null;
		int selection = 0;

		do {
			System.out.print(repo.toStringAnswers());
			System.out.println("Select an answer: ");
			selection = input.nextInt();
			
			if(selection <= 1) //Defaults
				ans = null;
			else
				ans = repo.getAnswerById(selection);

			if (ans == null)
				System.out.println("Error! Answer dosen't exist!");

		} while (ans == null);

		return ans.getText();
	}

	/**
	 * Public helper method Generates new answers objects of the default answers
	 * based of question data provided
	 * 
	 * @param noneCorrect        The boolean field of first default answers
	 * @param moreThenOneCorrect The boolean field of second default answers
	 * @return Both answers objects
	 */
	//TODO: update comment
	public ArrayList<Answer> generateDefaultAnswers(boolean noneCorrect, boolean moreThenOneCorrect) {
		ArrayList<Answer> defaults = new ArrayList<>();
		Answer temp = new Answer(getAnswerById(0));
		temp.setCorrect(noneCorrect);
		
		defaults.add(temp);
		
		temp = new Answer(getAnswerById(1));
		temp.setCorrect(moreThenOneCorrect);
		defaults.add(temp);
		
		return defaults;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Repo))
			return false;

		Repo other = (Repo) obj;
		
		return questions.equals(other.questions);
	}

}
