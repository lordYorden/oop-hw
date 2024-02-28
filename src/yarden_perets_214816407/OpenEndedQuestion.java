package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Objects;

public class OpenEndedQuestion extends Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = Repo.REPO_VERSION;
	private String answer;

	public OpenEndedQuestion(String text, String solution, Difficulty difficulty) {
		super(text, difficulty);
		this.answer = solution;
	}

	public OpenEndedQuestion(OpenEndedQuestion other) {
		super(other.text, other.difficulty);
		this.answer = other.answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSolution() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.getSolution());
		builder.append(this.answer);
		builder.append("\n\n");
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("\n");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answer);
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
		OpenEndedQuestion other = (OpenEndedQuestion) obj;
		return Objects.equals(answer, other.answer);
	}

}
