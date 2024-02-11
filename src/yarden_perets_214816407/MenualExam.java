package yarden_perets_214816407;
import java.io.IOException;
import java.util.Scanner;

public class MenualExam extends Exam {

	private Scanner input;
	
	public MenualExam(int maxNumQue, Scanner input) throws NumOfQuestionsException {
		super(maxNumQue);
		this.input = input;
	}

	@Override
	public void createExam(Repo repo) throws IOException {
		Question fromRepo = null;

		for (int i = 0; i < maxNumQue; i++) {
			fromRepo = Repo.selectQuestionFromRepo(repo, input);

			if (fromRepo instanceof OpenEndedQuestion) {
				
				try {
					this.addQuestion(new OpenEndedQuestion((OpenEndedQuestion) fromRepo));
					System.out.println("The question was successfully added!");
				} catch (NumOfQuestionsException e) {
					System.out.println("Error! " + e);
					break;
				}
			} else {
				MultiSelectQuestion toAdd = new MultiSelectQuestion((MultiSelectQuestion) fromRepo);
	
				try {
					MultiSelectQuestion.deleteAnswerFromAQuestion(toAdd, input);
					this.addQuestion(toAdd, repo);
					System.out.println("The question was successfully added!");
					
				} catch (NumOfAnswersException e) {
					System.out.println("Error! " + e.getMessage());
					System.out.println("Press any key to continue...");
					System.in.read();
					i--;
					continue;
	
				} catch (NumOfQuestionsException e) {
					System.out.println("Error! " + e.getMessage());
					System.out.println("Press any key to continue...");
					System.in.read();
					break;
				}
			}
			
			if(questions.size() != i+1) 
			{
				System.out.println("Error! Duplicate question cannot be entered!");
				System.out.println("Press any key to continue...");
				System.in.read();
				i--;//duplicate
			}
		}

	}
	
	@Deprecated
	public static void deleteAnswerFromAQuestion(MultiSelectQuestion multiQue, Scanner input) throws NumOfAnswersException {
		boolean answerExist = true;
		int selection = 0;
		
		int numOfAns = multiQue.getNumAnswers();
		if(numOfAns <= 3) 
			return;
		
		do {
			System.out.println(multiQue.toString());
			System.out.println("Select Answers to remove (-1 to continue): ");
			selection = input.nextInt();
			input.nextLine();
			
			if (selection != -1)
				answerExist = multiQue.deleteAnswerById(selection);

			if (!answerExist)
				System.out.println("Error! Answer dosen't exist!");

		} while (selection != -1 || !answerExist);
	}

}
