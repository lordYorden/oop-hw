package yarden_perets_214816407;

import java.util.Scanner;

public class AddQuestion implements ICommand {

	private Scanner input;

	public AddQuestion(Scanner input) {
		this.input = input;
	}

	@Override
	public void execute() {
		String text = "";
		boolean isValid = true;

		do {

			QuestionType type = QuestionType.getQuestionTypeFromUser(input);
			Difficulty diff = Difficulty.getDifficultyFromUser(input);

			System.out.println("Enter a question: ");
			text = input.nextLine();

			String solution = null;
			if (type == QuestionType.OpenEnded) {
				System.out.println("Enter The school solution: ");
				solution = input.nextLine();// inputPargraph(input);
			}

			try {
				if (QuestionFactory.createQuestion(type, diff, text, solution) == null) {
					isValid = false; // not possible but i still checked
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				isValid = false;
			}

		} while (!isValid);

	}
}
