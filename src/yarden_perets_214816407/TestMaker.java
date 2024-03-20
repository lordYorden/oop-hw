package yarden_perets_214816407;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestMaker {
	static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		Subject subject = Subject.getSubjectFromUser(input);
		//facade pattern
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		ActionType selction = null;
		
		//command pattern
		Map<ActionType, ICommand> actions = new HashMap<>();
		actions.put(ActionType.AddAnswer, new AddAnswer(input));
		actions.put(ActionType.AddQuestion, new AddQuestion(input));
		actions.put(ActionType.DeleteQuestion, new DeleteQuestion(input));
		
		//Observer pattern
		ActionCompleteObserver listener = new MenuActionCompleteListener();
		
		/*
		 * even restores data by itself
		*/
		facade.load(subject); //works by subject enjoy :)
		do {
			System.out.println("\nWelcome to my Test Maker!");
			selction = ActionType.getActionTypeFromUser(input);

			switch (selction) {
			case PrintQuestions: {
				System.out.println(facade);
				break;
			}
			//used as command
			case AddQuestion:
			case DeleteQuestion:
			case AddAnswer:
				actions.get(selction).execute();
				listener.update(selction);
				break;
			case AppendAnswerToQuestion: {
				appendAnswerToQuestion();
				break;
			}
//			case AddQuestion: {
//				addQuestion();
//				break;
//			}
			case DeleteAnswerFromAQuestion: {
				deleteAnswerFromAQuestion();
				break;
			}
//			case DeleteQuestion: {
//				deleteQuestion();
//				break;
//			}
			case GenerateTest: {
				generateTest();
				break;
			}
			case Exit: {
				System.out.println("Goodbye!");
				break;
			}
			default:
				System.out.println("Error! option dose not exist, Please try again!");
			}
		} while (selction != ActionType.Exit);

		facade.save();
	}
	
	/**
	 * Generate a test based on user input and writes it into a solution and exam
	 * files
	 * 
	 * @param repo the program's repository
	 * @throws IOException
	 * @throws NumOfAnswersException
	 */
	private static void generateTest() throws IOException {
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			return;
		
		int numQue = 0;

		do {
			System.out.println("How many question would be in the test? ");
			numQue = input.nextInt();
			input.nextLine();

			if (numQue <= 0)
				System.out.println("Error! Invalid number, Please try again!");

		} while (numQue <= 0);

		System.out.println("Do you want to generate the exam automatically? (true/false)");
		boolean isAuto = input.nextBoolean();
		
		try {
			String examPath = facade.generateTest(numQue, isAuto);
			System.out.println("Test written seccefully you can find it in " + examPath);
			System.out.println("Press any key to continue...");
			System.in.read();
		}catch (NumOfQuestionsException e) {
			System.out.println("Error! " + e.getMessage());
		}
		
		return;

	}

	//replaced with command
	@Deprecated
	@SuppressWarnings("unused")
	private static void deleteQuestion() {
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
//
	/**
	 * Ask the user to user to select a question the an answer from the question and
	 * removes it from the question's possible answers
	 * 
	 * @param repo the program's repository
	 */
	private static void deleteAnswerFromAQuestion() {
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			return;
		
		Question que = facade.selectQuestion();
		facade.removeAnswersFromQuestion(que);

	}

	/**
	 * Ask the user to user to select a question and an answer from the repo then
	 * adds the answer to the question's possible answers
	 * 
	 * @param repo the program's repository
	 */
	private static void appendAnswerToQuestion() {
		
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			return;
		
		Question que = facade.selectQuestion();
		Answer ans = facade.selectAnswer();
		
		System.out.println("Is the answers youv'e picked correct? (true/false): ");
		boolean isCorrect = input.nextBoolean();

		if (!(que instanceof MultiSelectQuestion)) {
			System.out.println("Error! That's not a Multi Select Question, Can't add answers!");
			return;
		}


		
		ans = new Answer(ans);
		ans.setCorrect(isCorrect);

		boolean res = ((MultiSelectQuestion) que).addAnswer(ans);

		if (!res)
			System.out.println("Error! An Error aacured while adding the question! Might be duplicate answer or Full Capcity reached!");

	}

	//replaced with command
	@Deprecated
	@SuppressWarnings("unused")
	private static void addQuestion() {
		String text = "";
		boolean isValid = true;

		do {
			
			QuestionType type = QuestionType.getQuestionTypeFromUser(input);
			Difficulty diff = Difficulty.getDifficultyFromUser(input);
			
			System.out.println("Enter a question: ");
			text = input.nextLine();
			
			String solution = null;
			if(type == QuestionType.OpenEnded) {
				System.out.println("Enter The school solution: ");
				solution = input.nextLine();// inputPargraph(input);
			}
			
			try {
				if(QuestionFactory.createQuestion(type, diff, text, solution) == null) {
					isValid = false; //not possible but i still checked
				}
			}catch (RuntimeException e) {
				System.out.println(e.getMessage());
				isValid =false;
			}
			
		}while(!isValid);

	}

	//replaced with command
	@Deprecated
	@SuppressWarnings("unused")
	private static void addAnswer() {
		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			return;
		
		System.out.println("Enter an answer: ");
		String newAnswer = input.nextLine();
		facade.addAnswer(AnswerFactory.createAnswer(newAnswer, false));
	}

	//replaced with enum
	@Deprecated
	@SuppressWarnings("unused")
	private static void printMenu() {
		System.out.println("\nWelcome to my Test Maker!");
		System.out.println("Please select an option: (-1 to exit)");
		System.out.println("1. Display all of the question from the repo (and their answers)");
		System.out.println("2. Add a new answers to the repo");
		System.out.println("3. Append an answer to an existing question");
		System.out.println("4. Add a new question");
		System.out.println("5. Delete an answers to a question");
		System.out.println("6. Delete a question");
		System.out.println("7. Generate a new test");
		System.out.println("-1 to exit");
	}

	// didn't use at the end
	@SuppressWarnings("unused")
	private static String inputPargraph() {
		StringBuffer buffer = new StringBuffer();
		String s = "";
		boolean fin = false;

		do {

			String line = input.nextLine();
			if (line.equals("end")) {
				if (buffer.isEmpty())
					System.out.println("Error! Noting was entered, Please try again!");
				else
					fin = true;
			} else {
				buffer.append(line);
				buffer.append("\n");
			}
		} while (!fin || buffer.isEmpty());

		return buffer.toString();
	}
	
}
