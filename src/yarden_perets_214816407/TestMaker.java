package yarden_perets_214816407;

import java.io.IOException;
import java.util.Scanner;

public class TestMaker {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		Repo repo = new Repo();
		final int EXIT = -1;
		int selction = 0;

		hardCoddedQue(repo);

		do {
			printMenu();
			selction = input.nextInt();
			input.nextLine();

			switch (selction) {
			case 1: {
				System.out.print(repo.toString());
				break;
			}
			case 2: {
				addAnswer(repo);
				break;
			}
			case 3: {
				appendAnswerToQuestion(repo);
				break;
			}
			case 4: {
				addQuestion(repo);
				break;
			}
			case 5: {
				deleteAnswerFromAQuestion(repo);
				break;
			}
			case 6: {
				deleteQuestion(repo);
				break;
			}
			case 7: {
				generateTest(repo);
				break;
			}
			case EXIT: {
				System.out.println("Goodbye!");
				break;
			}
			default:
				System.out.println("Error! option dose not exist, Please try again!");
			}
		} while (selction != EXIT);
	}

	/**
	 * Generate a test based on user input and writes it into a solution and exam
	 * files
	 * 
	 * @param repo the program's repository
	 * @throws IOException
	 */
	public static void generateTest(Repo repo) throws IOException {

		Scanner input = new Scanner(System.in);
		int numQue = 0;

		do {
			System.out.println("How many question would be in the test? ");
			numQue = input.nextInt();
			
			if(numQue <= 0)
				System.out.println("Error! Invalid number, Please try again!");
			
		} while (numQue <= 0);

		Test test = new Test(numQue);
		// Test test = repo.generateTest(numQue);
		Question toAdd = null;

		for (int i = 0; i < numQue; i++) {
			toAdd = new Question(selectQuestionFromRepo(repo));
			boolean answerExist = true;
			int selection = 0;

			do {
				System.out.println(toAdd.toString());
				System.out.println("Select Answers to remove (-1 to continue): ");
				selection = input.nextInt();
				if (selection != -1)
					answerExist = toAdd.deleteAnswerByIndex(--selection);

			} while (selection != -1 || !answerExist);

			int numCorrect = toAdd.getNumCorrect();
			Answer[] defaults = repo.generateDefaultAnswers((numCorrect == 0), (numCorrect > 1));
			toAdd.addAnswer(defaults[0]);
			toAdd.addAnswer(defaults[1]);
			test.addQuestion(toAdd);
		}

		test.writeTest(true); // write solution
		String examPath = test.writeTest(false); // write exam
		System.out.println("Test written seccefully you can find it in " + examPath);
		System.out.println("Press any key to continue...");
		System.in.read();
	}

	/**
	 * Ask the user to user to select a question and then deletes it from the repo
	 * 
	 * @param repo the program's repository
	 */
	private static void deleteQuestion(Repo repo) {
		Scanner input = new Scanner(System.in);
		boolean questionExist = false;
		int selection = 0;

		do {
			System.out.println(repo.toString());
			System.out.println("Select an Question to remove: ");
			selection = input.nextInt();
			questionExist = repo.deleteQuestionByIndex(--selection);

			if (!questionExist)
				System.out.println("Error! Question dosen't exist!");

		} while (!questionExist);

	}

	/**
	 * Ask the user to user to select a question the an answer from the question and
	 * removes it from the question's possible answers
	 * 
	 * @param repo the program's repository
	 */
	private static void deleteAnswerFromAQuestion(Repo repo) {
		Scanner input = new Scanner(System.in);
		Question que = selectQuestionFromRepo(repo);
		boolean answerExist = false;
		int selection = 0;

		do {
			System.out.println(que.toString());
			System.out.println("Select an Answers to remove: ");
			selection = input.nextInt();
			answerExist = que.deleteAnswerByIndex(--selection);

			if (!answerExist)
				System.out.println("Error! Answer dosen't exist!");

		} while (!answerExist);

	}

	/**
	 * Ask the user to user to select a question and an answer from the repo then
	 * adds the answer to the question's possible answers
	 * 
	 * @param repo the program's repository
	 */
	public static void appendAnswerToQuestion(Repo repo) {
		Scanner input = new Scanner(System.in);
		Question que = selectQuestionFromRepo(repo);
		String ans = selectAnswerFromRepo(repo);

		System.out.println("Is the answers youv'e picked correct? (true/false): ");
		boolean isCorrect = input.nextBoolean();

		boolean res = que.addAnswer(new Answer(ans, isCorrect));

		if (!res)
			System.out.println("Error! No more question could be added, reached full capacity");

	}

	/**
	 * Asks the user for a question and adds it to the repo
	 * 
	 * @param repo the program's repository
	 */
	private static void addQuestion(Repo repo) {
		Scanner input = new Scanner(System.in);
		String newQuestion = "";
		boolean res = false;

		do {
			System.out.println("Enter a question: ");
			newQuestion = input.nextLine();
			res = repo.addQuestion(new Question(newQuestion));
			if (!res)
				System.out.println("An error occurred while creating the question! Please try again!");

		} while (!res);

	}

	/**
	 * Asks the user for an answers and adds it to the repo
	 * 
	 * @param repo the program's repository
	 */
	private static void addAnswer(Repo repo) {
		Scanner input = new Scanner(System.in);
		boolean res = true;
		String newAnswer = "";

		do {
			System.out.println("Enter an answer: ");
			newAnswer = input.nextLine();
			res = repo.addAnswer(newAnswer);

			if (!res)
				System.out.println("Error! Answer already exists!");

		} while (!res);

	}

	/**
	 * Adds the hard codded questions to the repo
	 * 
	 * @param repo the program's repository
	 */
	public static void hardCoddedQue(Repo repo) {
		Question[] questions = new Question[20];

		questions[0] = new Question(
				"What year did the Titanic sink in the Atlantic Ocean on 15 April, on its maiden voyage from Southampton?");
		questions[0].addAnswer(new Answer("1908", false));
		questions[0].addAnswer(new Answer("1912", true));
		questions[0].addAnswer(new Answer("1914", false));
		questions[0].addAnswer(new Answer("1920", false));
//		Answer: B
		questions[1] = new Question("What is the name of the biggest technology company in South Korea?");
		questions[1].addAnswer(new Answer("Samsung", true));
		questions[1].addAnswer(new Answer("LG", false));
		questions[1].addAnswer(new Answer("Hyundai", false));
		questions[1].addAnswer(new Answer("SK", false));
//		Answer: A
		questions[2] = new Question("What is the name of the largest bone in the human body?");
		questions[2].addAnswer(new Answer("Humerus", false));
		questions[2].addAnswer(new Answer("Tibia", false));
		questions[2].addAnswer(new Answer("Femur", true));
		questions[2].addAnswer(new Answer("Pelvis", false));
//		Answer: C	
		questions[3] = new Question("Which metal was discovered by Hans Christian Oersted in 1825?");
		questions[3].addAnswer(new Answer("Copper", false));
		questions[3].addAnswer(new Answer("Zinc", false));
		questions[3].addAnswer(new Answer("Nickel", false));
		questions[3].addAnswer(new Answer("Aluminium", true));
//		Answer: D
		questions[4] = new Question("How many breaths does the human body take daily?");
		questions[4].addAnswer(new Answer("About 10,000", false));
		questions[4].addAnswer(new Answer("About 20,000", true));
		questions[4].addAnswer(new Answer("About 30,000", false));
		questions[4].addAnswer(new Answer("About 50,000", false));
//		Answer: B
		questions[5] = new Question("Who was Prime Minister of Great Britain from 1841 to 1846?");
		questions[5].addAnswer(new Answer("William Gladstone", false));
		questions[5].addAnswer(new Answer("Benjamin Disraeli", false));
		questions[5].addAnswer(new Answer("Robert Peel", true));
		questions[5].addAnswer(new Answer("Lord Palmerston", false));
//		Answer: C
		questions[6] = new Question("What is the name of the largest desert in the world?");
		questions[6].addAnswer(new Answer("Sahara", false));
		questions[6].addAnswer(new Answer("Gobi", false));
		questions[6].addAnswer(new Answer("Kalahari", false));
		questions[6].addAnswer(new Answer("Antarctic", true));
//		Answer: D
		questions[7] = new Question("Who was the first person in space?");
		questions[7].addAnswer(new Answer("Yuri Gagarin  ", false));
		questions[7].addAnswer(new Answer("Neil Armstrong", false));
		questions[7].addAnswer(new Answer("John Glenn    ", false));
		questions[7].addAnswer(new Answer("Alan Shepard  ", false));
//		Answer: A
		questions[8] = new Question("What is the chemical symbol of Tungsten?");
		questions[8].addAnswer(new Answer("Tn", false));
		questions[8].addAnswer(new Answer("Tu", false));
		questions[8].addAnswer(new Answer("Tg", false));
		questions[8].addAnswer(new Answer("W ", true));
		// Answer: D
		questions[8] = new Question("What do you use to measure rainfall?");
		questions[8].addAnswer(new Answer("Barometer  ", false));
		questions[8].addAnswer(new Answer("Hygrometer ", false));
		questions[8].addAnswer(new Answer("Pluviometer", true));
		questions[8].addAnswer(new Answer("Thermometer", false));
		// Answer: C
		questions[9] = new Question("Which is the smallest planet in our solar system?");
		questions[9].addAnswer(new Answer("Venus  ", false));
		questions[9].addAnswer(new Answer("Mars   ", false));
		questions[9].addAnswer(new Answer("Mercury", true));
		questions[9].addAnswer(new Answer("Pluto  ", false));
		// Answer: C
		questions[10] = new Question("Which metal has the chemical symbol Pb?");
		questions[10].addAnswer(new Answer("Platinum  ", false));
		questions[10].addAnswer(new Answer("Potassium ", false));
		questions[10].addAnswer(new Answer("Phosphorus", false));
		questions[10].addAnswer(new Answer("Lead      ", true));
		// Answer: D
		questions[11] = new Question("What is a group of foxes called?");
		questions[11].addAnswer(new Answer("Pack ", false));
		questions[11].addAnswer(new Answer("Troop", false));
		questions[11].addAnswer(new Answer("Skulk", true));
		questions[11].addAnswer(new Answer("Flock", false));
		// Answer: C
		questions[12] = new Question("Which film won the Best Picture award at the 2020 Oscars?");
		questions[12].addAnswer(new Answer("Joker                        ", false));
		questions[12].addAnswer(new Answer("Parasite                     ", true));
		questions[12].addAnswer(new Answer("1917                         ", false));
		questions[12].addAnswer(new Answer("Once Upon a Time in Hollywood", false));
//		Answer: B
		questions[13] = new Question("Which country is the largest producer of coffee in the world?");
		questions[13].addAnswer(new Answer("Brazil", true));
		questions[13].addAnswer(new Answer("Colombia", false));
		questions[13].addAnswer(new Answer("Vietnam ", false));
		questions[13].addAnswer(new Answer("Ethiopia", false));
//		Answer: A
		questions[14] = new Question("In which year did World War II end?");
		questions[14].addAnswer(new Answer("1939", false));
		questions[14].addAnswer(new Answer("1943", false));
		questions[14].addAnswer(new Answer("1945", true));
		questions[14].addAnswer(new Answer("1947", false));
		// Answer: C
		questions[15] = new Question("What is the name of the currency used in Japan?");
		questions[15].addAnswer(new Answer("Yuan ", false));
		questions[15].addAnswer(new Answer("Won  ", false));
		questions[15].addAnswer(new Answer("Yen  ", true));
		questions[15].addAnswer(new Answer("Rupee", false));
		// Answer: C
		questions[16] = new Question(
				"Which country is the only one to border both the Atlantic Ocean and the Indian Ocean?");
		questions[16].addAnswer(new Answer("Brazil      ", false));
		questions[16].addAnswer(new Answer("India       ", false));
		questions[16].addAnswer(new Answer("Australia   ", false));
		questions[16].addAnswer(new Answer("South Africa", true));
		// Answer: D
		questions[17] = new Question("Which author wrote The Hunger Games trilogy?");
		questions[17].addAnswer(new Answer("J.K. Rowling      ", false));
		questions[17].addAnswer(new Answer("George R.R. Martin", false));
		questions[17].addAnswer(new Answer("Suzanne Collins   ", true));
		questions[17].addAnswer(new Answer("Stephenie Meyer   ", false));
		// Answer: C
		questions[18] = new Question("Which sport is played on a court with a net and a shuttlecock?");
		questions[18].addAnswer(new Answer("Tennis    ", false));
		questions[18].addAnswer(new Answer("Volleyball", false));
		questions[18].addAnswer(new Answer("Badminton ", true));
		questions[18].addAnswer(new Answer("Squash    ", false));
		// Answer: C

		for (int i = 0; i < questions.length; i++) {
			repo.addQuestion(questions[i]);
		}
	}

	/**
	 * Prints the program menu
	 */
	public static void printMenu() {
		System.out.println("\nWelcome to my Test Maker!");
		System.out.println("Please select an option: (-1 to exit)");
		System.out.println("1. Display all of the question from the repo (and their answers)");
		System.out.println("2. Add a new answers to the repo");
		System.out.println("3. Append an answer to an existing question");
		System.out.println("4. Add a new question");
//		System.out.println("4. Update an exsisting question");
//		System.out.println("5. Update an exsisting answer to a question");
		System.out.println("5. Delete an answers to a question");
		System.out.println("6. Delete a question");
		System.out.println("7. Generate a new test");
		// System.out.println("-1 to exit");
	}

	/**
	 * Ask the user to select an answer from the repo and retrives it
	 * 
	 * @param repo the program's repository
	 * @return the question that was selected
	 */
	private static Question selectQuestionFromRepo(Repo repo) {
		Scanner input = new Scanner(System.in);
		Question que = null;
		int selection = 0;

		do {
			System.out.print(repo.toString());
			System.out.println("Select a question: ");
			selection = input.nextInt();
			que = repo.getQuestionByIndex(--selection);

			if (que == null)
				System.out.println("Error! Question dosen't exist!");

		} while (que == null);

		return que;
	}

	/**
	 * Ask the user to select a question from the repo and retrives it
	 * 
	 * @param repo the program's repository
	 * @return the answers that was selected
	 */
	private static String selectAnswerFromRepo(Repo repo) {
		Scanner input = new Scanner(System.in);
		String ans = null;
		int selection = 0;

		do {
			System.out.print(repo.toStringAnswers());
			System.out.println("Select an answer: ");
			selection = input.nextInt();
			ans = repo.getAnswerByIndex(--selection);

			if (ans == null)
				System.out.println("Error! Answer dosen't exist!");

		} while (ans == null);

		return ans;
	}

}
