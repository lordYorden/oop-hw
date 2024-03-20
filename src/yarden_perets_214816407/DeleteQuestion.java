package yarden_perets_214816407;

import java.util.Scanner;

public class DeleteQuestion implements ICommand {

	private Scanner input;

	public DeleteQuestion(Scanner input) {
		this.input = input;
	}

	@Override
	public void execute() {
		boolean questionExist = false;
		int id = 0;
		
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			return;
		
		if(facade.isQuestionsEmpty()) {
			System.out.println("Error! No questions to remove!");
			return;
		}

		do {
			System.out.println(facade);
			
			System.out.println("Select an Question to remove: ");
			id = input.nextInt();
			input.nextLine();
			questionExist = facade.deleteQuestion(id);

			if (!questionExist)
				System.out.println("Error! Question dosen't exist!");

		} while (!questionExist);
	}
}
