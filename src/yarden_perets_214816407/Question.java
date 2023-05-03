package yarden_perets_214816407;

public class Question {
	
	public enum Difficulty {Easy, Moderate, Hard}
	private static int numQuestions;
	
	protected String text;
	protected int id;
	protected Difficulty difficulty;

	/**
	 * C'tor
	 * 
	 * @param	text the question itself
	 */
	Question(String text, Difficulty difficulty) {
		this.text = text;
		this.id = numQuestions++;
		this.difficulty = difficulty;
	}

	/**
	 * copy c'tor
	 * 
	 * @param other   object to copy
	 */
	Question(Question other) {
		this(other.text, other.difficulty);
	}

	/**
	 * @return the question itself
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @return object values
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id + ". ");
		builder.append(text);
		builder.append("\n");
		builder.append("Difficulty: ");
		builder.append(difficulty.name());
		builder.append("\n");
		return builder.toString();
	}
}
