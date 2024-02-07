package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;

public class MultiSelectQuestion extends Question implements Serializable, Iterable<Answer> {
	private LinkedHashSet<Answer> answers;
	private int numCorrect;
	public static final int maxAnswersCpacity = 10;

	/**
	 * C'tor
	 * 
	 * @param text the question itself
	 */
	MultiSelectQuestion(String text, Difficulty difficulty) {
		super(text, difficulty);
		this.numCorrect = 0;
		this.answers = new LinkedHashSet<>();
	}

	/**
	 * copy c'tor
	 * 
	 * @param other object to copy
	 */
	MultiSelectQuestion(MultiSelectQuestion other) {
		super(other.text, other.difficulty);
		this.numCorrect = other.numCorrect;
		this.answers = new LinkedHashSet<>(other.answers);
	}

	//for hard coded questions
	public MultiSelectQuestion(String text, Difficulty difficulty, Answer[] answers) {
		this(text, difficulty);
		//count correct
		for (Answer answer : answers) {
			addAnswer(answer);
		}
	}

	/**
	 * @return the question's possible answers
	 */
	@Deprecated
	public LinkedHashSet<Answer> getAnswers() {
		return answers;
	}

	public void clear() {
		this.answers.clear();
		this.numCorrect = 0;
	}

	/**
	 * @return number of possible answers
	 */
	public int getNumAnswers() {
		return answers.size();
	}

	/**
	 * @return the number of correct answers
	 */
	public int getNumCorrect() {
		return numCorrect;
	}

//	/**
//	 * @param displayAnswers whether to display the answers to every question
//	 * @return object values
//	 */
//	public String toStringSolution() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(super.toString());
//
//		for (int i = 0; i < numAnswers; i++) {
//			builder.append("\s\s\s");
//			builder.append((char)('A'+i));
//			builder.append(". ");
//			builder.append(answers[i].toString(true));
//		}
//		builder.append("\n");
//		return builder.toString();
//	}

	/**
	 * @return object values
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());

		int i = 1;
		for (Answer answer : answers) {
			builder.append(i);//for a nice print
			builder.append(". ");
			answer.setDisplaySolution(displaySolution);
			builder.append(answer.toString());
			i++;
		}
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
		
		if (answers.size() >= maxAnswersCpacity) {
			return false;// array full
		}
		
		//ansToAdd.setId(answers.size() + 1);//for a nice print
		if (ansToAdd.isCorrect()) {
			numCorrect++;
		}
		
		return answers.add(ansToAdd);
	}

	/**
	 * deletes an answer
	 * 
	 * @param index answer to be deleted
	 * @return whether the answer was deleted
	 */
	//TODO: figure out how to resolve id
	public boolean deleteAnswerById(int id) {
		if(answers.isEmpty())
			return false;
		
		Answer ans = getAnswerById(id);
		if(ans != null && ans.isCorrect())
			numCorrect--;
		
		return answers.remove(ans);
	}
	
	public Answer getAnswerById(int id) {		
		for(Answer answer : answers) {
			if(answer.getId() == id)
				return answer;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answers, numCorrect);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiSelectQuestion other = (MultiSelectQuestion) obj;
		return Objects.equals(answers, other.answers) && numCorrect == other.numCorrect;
	}

	@Override
	public Iterator<Answer> iterator() {
		return answers.iterator();
	}
	
	public static void deleteAnswerFromAQuestion(MultiSelectQuestion multiQue, Scanner input){
		boolean answerExist = true;
		int selection = 0;
		
		if (multiQue.getNumAnswers() == 0) {
			System.out.println("Error! No answers to remove!");
			return;
		}
		
		do {
			Iterator<Answer> it = multiQue.iterator();
			
			while(it.hasNext()) {
				Answer ans = it.next();
				ans.setDisplaySolution(false);
				System.out.printf("ID: %d\n%s\n",ans.getId(), ans);
			}
			
			System.out.println("Select Answers to remove (-1 to continue): ");
			selection = input.nextInt();
			input.nextLine();
			
			if (selection != -1)
				answerExist = multiQue.deleteAnswerById(selection);

			if (!answerExist)
				System.out.println("Error! Answer dosen't exist!");

		} while (selection != -1 || !answerExist);
	}

}
