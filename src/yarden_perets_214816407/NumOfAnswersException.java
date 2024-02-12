package yarden_perets_214816407;

public class NumOfAnswersException extends ExamCreationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = Repo.REPO_VERSION;
	private int numOfAnswers;

	/**
	 * @param numOfAnswers
	 */
	public NumOfAnswersException(int numOfAnswers) {
		super("Failed adding the question because it contains only " + numOfAnswers
				+ " when the requirement is at least 4!");
		this.setNumOfAnswers(numOfAnswers);
	}

	public int getNumOfAnswers() {
		return numOfAnswers;
	}

	public void setNumOfAnswers(int numOfAnswers) {
		this.numOfAnswers = numOfAnswers;
	}
}
