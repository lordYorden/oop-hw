package yarden_perets_214816407;
import java.util.Scanner;

public class MenualExam extends Exam {

	private Scanner input;
	
	public MenualExam(int maxNumQue, int numQuestions, Scanner input) throws NumOfQuestionsException {
		super(maxNumQue, numQuestions);
		this.input = input;
	}

	@Override
	public Question getQuestion(ElementManager<Question> questions, ElementManager<Answer> answers) {
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			return null;
		
		Question fromRepo = Repo.selectQuestionFromRepo(questions, input);
		if(fromRepo == null){
			System.out.println("Error! Somthing went worng while Adding question!");
			return null;
		}

		Question toAdd = QuestionFactory.createQuestion(fromRepo);
		
		if (fromRepo instanceof MultiSelectQuestion) {		
			facade.removeAnswersFromQuestion(toAdd);
//			try {
//				MultiSelectQuestion.deleteAnswersFromAQuestion(toAdd, input);
//			} catch (NumOfAnswersException e) {
//				System.out.println("Error! " + e.getMessage());
//				System.out.println("Press any key to continue...");
//				input.nextLine();
//				return null;
//			}
//			
//			return toAdd; //new Multi select
		}
		
		return toAdd;/*new OpenEndedQuestion((OpenEndedQuestion) fromRepo); //new open ended
*/	}
			

}
