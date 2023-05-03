package yarden_perets_214816407;

public class OpenEndedQuestion extends Question{

	private String solution;

	public OpenEndedQuestion(String text, Difficulty difficulty, String solution) {
		super(text, difficulty);
		this.solution = solution;
	}

	public String getSolution() {
		return solution;
	}

	public String toStringSolution() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(solution);
		return builder.toString();
	}
	
	
	
}
