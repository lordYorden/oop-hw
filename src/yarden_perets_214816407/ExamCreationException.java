package yarden_perets_214816407;

public class ExamCreationException extends Exception {

	public ExamCreationException(String message) {
		super(message);
	}
	
	public ExamCreationException() {
		super("General ExamCreationException: Failed to create an Exam!");
	}
}
