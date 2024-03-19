package yarden_perets_214816407;
import java.util.Scanner;

public class MenualExam extends Exam {

	private Scanner input;
	
	public MenualExam(int maxNumQue, Scanner input) throws NumOfQuestionsException {
		super(maxNumQue);
		this.input = input;
	}

	@Override
	public Question getQuestion(ElementManager<Question> questions, ElementManager<Answer> answers) {
		Question fromRepo = Repo.selectQuestionFromRepo(questions, input);
		if(fromRepo == null){
			System.out.println("Error! Somthing went worng while Adding question!");
			return null;
		}

		if (fromRepo instanceof MultiSelectQuestion) {
			MultiSelectQuestion toAdd = new MultiSelectQuestion((MultiSelectQuestion) fromRepo);

			try {
				MultiSelectQuestion.deleteAnswersFromAQuestion(toAdd, input);
			} catch (NumOfAnswersException e) {
				System.out.println("Error! " + e.getMessage());
				System.out.println("Press any key to continue...");
				input.nextLine();
				return null;
			}
			
			return toAdd; //new Multi select
		}
		
		return new OpenEndedQuestion((OpenEndedQuestion) fromRepo); //new open ended
	}
			

}
