package yarden_perets_214816407;

public class Question {
	private String text;
	private int numAnswers;
	private Answer[] answers;
	private int numCorrect;

	/**
	 * C'tor
	 * 
	 * @param	text the question itself
	 */
	Question(String text) {
		this.text = text;
		this.numAnswers = 0;
		this.numCorrect = 0;
		this.answers = new Answer[10];
	}

	/**
	 * copy c'tor
	 * 
	 * @param other   object to copy
	 */
	Question(Question other) {
		this.text = other.text;
		this.numCorrect = other.numCorrect;
		this.numAnswers = other.numAnswers;
		this.answers = new Answer[10];
		for (int i = 0; i < other.numAnswers; i++) {
			this.answers[i] = new Answer(other.answers[i]);
		}
	}

	/**
	 * @return the question itself
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the question's possible answers
	 */
	public Answer[] getAnswers() {
		return answers;
	}

	/**
	 * @return number of possible answers
	 */
	public int getNumAnswers() {
		return numAnswers;
	}
	
	/**
	 * @return the number of correct answers
	 */
	public int getNumCorrect() {
		return numCorrect;
	}

	/**
	 * @param displayAnswers whether to display the answers to every question
	 * @return object values
	 */
	public String toString(boolean displayAnswers) {
		StringBuilder builder = new StringBuilder();
		builder.append(text);
		builder.append("\n");

		for (int i = 0; i < numAnswers; i++) {
			builder.append("\s\s\s");
			builder.append((char)('A'+i));
			builder.append(". ");
			builder.append(answers[i].toString(displayAnswers));
		}
		builder.append("\n");
		return builder.toString();
	}
	
	/**
	 * @return object values
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(text);
		builder.append("\n");

		for (int i = 0; i < numAnswers; i++) {
			builder.append(i + 1);
			builder.append(". ");
			builder.append(answers[i].toString(false));
		}
		builder.append("\n");
		return builder.toString();
	}

	/**
	 * adds and aswers to the question's possible answers
	 * 
	 * @param ansToAdd	answer given 
	 * @return whether the answer was added 
	 */
	public boolean addAnswer(Answer ansToAdd) {
		if (numAnswers >= answers.length) {
			return false;
		}

		answers[numAnswers++] = ansToAdd;
		if(ansToAdd.isCorrect()) {
			numCorrect++;
		}
		return true;
	}
	
	/**
	 * deletes an answer 
	 * @param index	 answer to be deleted
	 * @return whether the answer was deleted
	 */
	public boolean deleteAnswerByIndex(int index) {
		if (numAnswers <= 0 && answers != null) {
			//System.out.println("Error! No more answers left to remove!");
			return false;
		}
		
		if(numAnswers <= index || index < 0) {
			return false;
		}
		
		if(answers[index].isCorrect())
			numCorrect--;
		
		answers[index] = answers[--numAnswers];
		answers[numAnswers] = null;
		return true;
	}
}
