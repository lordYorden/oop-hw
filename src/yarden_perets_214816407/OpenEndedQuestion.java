package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Objects;

public class OpenEndedQuestion extends Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = Repo.REPO_VERSION;
	private String solution;

	public OpenEndedQuestion(String text, String solution, Difficulty difficulty) {
		super(text, difficulty);
		this.solution = solution;
	}

	public OpenEndedQuestion(OpenEndedQuestion other) {
		super(other.text, other.difficulty);
		this.solution = other.solution;
	}

	public String getSolution() {
		return solution;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		if (displaySolution) {
			builder.append("Solution: ");
			builder.append(solution);
			builder.append("\n");
		}
		builder.append("\n");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(solution);
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
		return Objects.equals(solution, other.solution);
	}

}
