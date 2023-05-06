package yarden_perets_214816407;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
	private String date;
	private int currNumQue;
	private Question[] questions;

	/**
	 * C'tor saves the date and time the test was created
	 * 
	 * @param maxNumQue max number of question in test
	 */
	public Test(int maxNumQue) {
		questions = new Question[maxNumQue];
		// this.maxNumQue = maxNumQue;
		currNumQue = 0;
		this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm"));
	}

	/**
	 * Adds a question to the test
	 * 
	 * @param queToAdd question given
	 * @return whether the question was added
	 */
	public boolean addQuestion(Question queToAdd) {
		if (currNumQue >= questions.length) {
			System.out.println("Error! No more question could be added, reached full capacity");
			return false;
		}

		questions[currNumQue++] = queToAdd;
		return true;
	}

	/**
	 * @param displayAnswers whether to display the answers to every question
	 * @return object values
	 */
	public String toString(boolean displayAnswers) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < currNumQue; i++) {
			builder.append(questions[i].toString(displayAnswers));
		}
//		builder.append(Arrays.toString(questions));
//		builder.append("]");
		return builder.toString();
	}

	/**
	 * writes the to string to a file in the specified format
	 * (exam/solution_yyyy_MM_dd_HH_mm)
	 * 
	 * @param displaySolution exam/solution
	 * @throws FileNotFoundException
	 */
	public String writeTest(boolean displaySolution) throws FileNotFoundException {
		String filePath = (displaySolution ? "solution_" : "exam_") + date + ".txt";
		File file = new File(filePath);
		PrintWriter pw = new PrintWriter(file);
		pw.write(this.toString(displaySolution));
		pw.close();
		return filePath;
	}

}
