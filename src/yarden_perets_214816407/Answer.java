package yarden_perets_214816407;

public class Answer {
	private String text;
	private boolean isCorrect;

	/**
	 * C'tor
	 * 
	 * @param answer    the answer text
	 * @param isCorrect whether the answer is correct
	 */
	public Answer(String answer, boolean isCorrect) {
		this.text = answer;
		this.isCorrect = isCorrect;
	}

	/**
	 * Copy c'tor
	 * 
	 * @param other answer to copy
	 */
	public Answer(Answer other) {
		this(other.text, other.isCorrect);
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
	 * @param displayAnswers whether to display the answers (true/false)
	 * @return object values
	 */
	public String toString(boolean displayAnswer) {
		StringBuilder builder = new StringBuilder();
		// builder.append("answer: ");
		builder.append(text);
		if (displayAnswer) {
			builder.append(" [");
			builder.append(isCorrect ? "x" : " ");
			builder.append("]");
		}

		builder.append("\n");
		return builder.toString();
	}

}
