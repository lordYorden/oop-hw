package yarden_perets_214816407;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Test {
	private String date;
	private int currNumQue;
	private Question[] questions;
	private boolean displaySolution;

	/**
	 * C'tor saves the date and time the test was created
	 * 
	 * @param maxNumQue max number of question in test
	 */
	public Test(int maxNumQue) {
		questions = new Question[maxNumQue];
		// this.maxNumQue = maxNumQue;
		displaySolution = false;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < currNumQue; i++) {
			questions[i].setDisplaySolution(displaySolution);
			builder.append(questions[i].toString());
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
		this.displaySolution = displaySolution;
		pw.write(this.toString());
		pw.close();
		return filePath;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Test))
			return false;
		
		Test other = (Test) obj;
		
		if(this.currNumQue != other.currNumQue) {
			return false;
		}
		
		return Arrays.equals(this.questions, other.questions);
	}

}
