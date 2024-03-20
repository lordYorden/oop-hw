package yarden_perets_214816407;

import java.util.Scanner;

public class AddAnswer implements ICommand {
	
	private Scanner input;

	public AddAnswer(Scanner input) {
		this.input = input;
	}

	@Override
	public void execute() {
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			return;
		
		System.out.println("Enter an answer: ");
		String newAnswer = input.nextLine();
		facade.addAnswer(AnswerFactory.createAnswer(newAnswer, false));
	}

}
