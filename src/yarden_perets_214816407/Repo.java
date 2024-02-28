package yarden_perets_214816407;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

//import java.util.Arrays;

//import java.util.Scanner;

public class Repo implements Serializable {

	public static final int REPO_VERSION = 4;//define the project version
	
	private static final long serialVersionUID = REPO_VERSION;

	public enum Subject {
		Math, Science, History, Geography
	}

	private LinkedHashSet<Answer> answers;
	private QuestionManager questions;/* LinkedHashSet<Question> questions; */
	private Subject subject;

	/**
	 * C'tor
	 * 
	 * Creates an answer array with the 2 default answers in the start
	 */
	public Repo(Subject subject) {
		this.answers = new LinkedHashSet<>();
		this.questions = new QuestionManager();
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
	public boolean addAnswer(Answer ansToAdd) {	
		ansToAdd.setId(answers.size()); //update id based on repo
		Answer newAns = new Answer(ansToAdd);
		newAns.setId(answers.size());
		return answers.add(newAns);
	}
	
	public boolean addAnswer(String ansToAdd) {	
		Answer newAns = new Answer(ansToAdd, false); //Default
		newAns.setId(answers.size());
		return answers.add(newAns);
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
		builder.append(questions.toString());
		
//		for(Question question : questions) {
//			question.setDisplaySolution(false);
//			builder.append(question.toString());
//		}
		return builder.toString();
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
			builder.append("ID: ");
			builder.append(ans.getId());
			builder.append("\n");
			builder.append(ans);
			builder.append("\n");
		}
		return builder.toString();
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
			que = repo.questions.getQuestion(selection);

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
	public static Answer selectAnswerFromRepo(Repo repo, Scanner input) {
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

		return ans;
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

	public QuestionManager getQuestions() {
		return questions;
	}
	
}
