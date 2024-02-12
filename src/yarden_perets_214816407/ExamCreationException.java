package yarden_perets_214816407;

public class ExamCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = Repo.REPO_VERSION;

	public ExamCreationException(String message) {
		super(message);
	}
	
	public ExamCreationException() {
		super("General ExamCreationException: Failed to create an Exam!");
	}
}
