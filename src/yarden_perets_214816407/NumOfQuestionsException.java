package yarden_perets_214816407;

public class NumOfQuestionsException extends ExamCreationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = Repo.REPO_VERSION;
	private int numOfQuestions;
	private int limit;

	public NumOfQuestionsException(int numOfQuestions) {
		super("Failed to create the Exam becuase the number of questions " + numOfQuestions
				+ " exceed the limitation of 10 questions per Exam!");
		this.setNumOfQuestions(numOfQuestions);
		this.setLimit(10);
	}

	public NumOfQuestionsException(int numOfQuestions, int limit) {
		super("Failed to add question to the Exam becuase the number of questions " + numOfQuestions
				+ " exceed the limit of " + limit + " questions! ");
		this.setNumOfQuestions(numOfQuestions);
		this.setLimit(limit);

	}

	public int getNumOfQuestions() {
		return numOfQuestions;
	}

	public void setNumOfQuestions(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
