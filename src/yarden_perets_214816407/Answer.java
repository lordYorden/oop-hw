package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable, Solutionable, Comparable<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = Repo.REPO_VERSION;
	private String text;
	private boolean isCorrect;
	private int id; //specific for every container

	/**
	 * C'tor
	 * 
	 * @param answer    the answer text
	 * @param isCorrect whether the answer is correct
	 */
	public Answer(String answer, boolean isCorrect) {
		this.text = answer;
		this.id = 0;
		this.isCorrect = isCorrect;
		//this.displaySolution = false;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	/**
	 * Copy c'tor
	 * 
	 * @param other answer to copy
	 */
	public Answer(Answer other) {
		this(other.text, other.isCorrect);
		this.id = other.id;
	}

	/**
	 * @return the answer text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return whether the answer is correct
	 */
	public boolean isCorrect() {
		return isCorrect;
	}

	/**
	 * @param isCorrect change the correctness of the answer
	 */
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * @return toString
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(text);
		builder.append("\n");
		return builder.toString();
	}



	@Override
	public int hashCode() {
		return Objects.hash(text, id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		return Objects.equals(text, other.text) && id == other.id;
	}


	@Override
	public String getSolution() {
		StringBuilder builder = new StringBuilder();
		builder.append(text);
		builder.append(" [");
		builder.append(isCorrect ? "x" : " ");
		builder.append("]\n");
		return builder.toString();
	}


	@Override
	public int compareTo(Integer id) {
		return this.id - id;
	}

}
