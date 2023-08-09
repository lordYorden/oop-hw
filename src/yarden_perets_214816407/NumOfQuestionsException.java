package yarden_perets_214816407;

public class NumOfQuestionsException extends ExamCreationException {

	private int numOfQuestions;
	private int limit;

	public NumOfQuestionsException(int numOfQuestions) {
		super("Failed to create the Exam bequse the number of questions " + numOfQuestions
				+ " exceed the limitation of 10 questions per Exam!");
		this.numOfQuestions = numOfQuestions;
		this.limit = 10;
	}

	public NumOfQuestionsException(int numOfQuestions, int limit) {
		super("Failed to add question to the Exam becuase the number of questions " + numOfQuestions
				+ " exceed the limitation of " + limit + " questions1 ");
		this.numOfQuestions = numOfQuestions;
		this.limit = limit;

	}
}
