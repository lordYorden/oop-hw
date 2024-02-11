package yarden_perets_214816407;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import yarden_perets_214816407.Question.Difficulty;
import yarden_perets_214816407.Repo.Subject;

public class TestMaker {
	static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		Subject subject = getSubjectFromUser();
		Repo repo = null;// new Repo(subject);
		final int EXIT = -1;
		int selction = 0;

		try {
			repo = loadRepo(subject);
		} catch (ClassNotFoundException | IOException e) {
			repo = new Repo(subject);
		}

		/*
		 * remove comment to restore lost info in the data
		 * after you restored it please comment again in order
		 * to not duplicate the questions in the db
		*/
		
		hardCodedQue(repo, subject); //works even by subject enjoy :)

		do {
			printMenu();
			selction = input.nextInt();
			input.nextLine();

			switch (selction) {
			case 1: {
				System.out.print(repo);
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
				saveRepo(repo);
				System.out.println("Goodbye!");
				break;
			}
			default:
				System.out.println("Error! option dose not exist, Please try again!");
			}
		} while (selction != EXIT);

		saveRepo(repo);
	}

	private static Repo loadRepo(Subject subject) throws FileNotFoundException, IOException, ClassNotFoundException {
		String filename = subject.name() + ".db";
		ObjectInputStream toLoad = new ObjectInputStream(new FileInputStream(filename));
		Repo repo = (Repo) toLoad.readObject();
		toLoad.close();
		Question.setNumQuestions(repo.getNumQuestions() + 1);
		return repo;
	}

	private static void saveRepo(Repo repo) throws FileNotFoundException, IOException {
		String filename = repo.getSubject().name() + ".db";
		ObjectOutputStream toSave = new ObjectOutputStream(new FileOutputStream(filename));
		toSave.writeObject(repo);
		toSave.close();
	}

	/**
	 * Generate a test based on user input and writes it into a solution and exam
	 * files
	 * 
	 * @param repo the program's repository
	 * @throws IOException
	 * @throws NumOfAnswersException
	 */
	public static void generateTest(Repo repo) throws IOException {
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

		Exam exam = null;
		try {
			if (isAuto) {
				exam = new AutomaticExam(numQue);
				//System.out.println("is auto");
			} else {
				exam = new MenualExam(numQue, input);
			}

		} catch (NumOfQuestionsException e) {
			System.out.println("Error! " + e.getMessage());
		}

		exam.createExam(repo);

		exam.writeExam(true); // write solution
		String examPath = exam.writeExam(false); // write exam
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
		boolean questionExist = false;
		int id = 0;

		do {
			System.out.println(repo.toString());
			System.out.println("Select an Question to remove: ");
			id = input.nextInt();
			input.nextLine();
			questionExist = repo.deleteQuestionById(id);

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
		Question que = Repo.selectQuestionFromRepo(repo, input);

		if (!(que instanceof MultiSelectQuestion)) {
			System.out.println("Error! Can't remove answers from a non Amercian Question!");
			return;
		}

		MultiSelectQuestion multiQue = (MultiSelectQuestion) que;
		MultiSelectQuestion.deleteAnswerFromAQuestion(multiQue, input);

	}

	/**
	 * Ask the user to user to select a question and an answer from the repo then
	 * adds the answer to the question's possible answers
	 * 
	 * @param repo the program's repository
	 */
	public static void appendAnswerToQuestion(Repo repo) {
		Question que = Repo.selectQuestionFromRepo(repo, input);
		Answer ans = Repo.selectAnswerFromRepo(repo, input);

		if (!(que instanceof MultiSelectQuestion)) {
			System.out.println("Error! That's not a Multi Select Question, Can't add answers!");
			return;
		}

		System.out.println("Is the answers youv'e picked correct? (true/false): ");
		boolean isCorrect = input.nextBoolean();
		
		ans = new Answer(ans);
		ans.setCorrect(isCorrect);

		boolean res = ((MultiSelectQuestion) que).addAnswer(ans);

		if (!res)
			System.out.println("Error! An Error aacured while adding the question! Might be duplicate answer or Full Capcity reached!");

	}

	/**
	 * Asks the user for a question and adds it to the repo
	 * 
	 * @param repo the program's repository
	 */
	private static void addQuestion(Repo repo) {
		String text = "";
		// boolean res = false;
		Question que = null;
		boolean isValid = false;
		int selction = 0;

		do {
			do {

				System.out.println("Select a question type: ");
				System.out.println("1. Multi-Selction question");
				System.out.println("2. Open-Ended question");
				selction = input.nextInt();
				input.nextLine();

				selction--;
				isValid = selction >= 0 && selction < 2;

				if (!isValid) {
					System.out.println("Error! Type dosen't exist, Try again!");
				}

			} while (!isValid);

			System.out.println("Enter a question: ");
			text = input.nextLine();
			Question.Difficulty diff = getDifficultyFromUser();

			if (selction != 0) {
				System.out.println("Enter The school solution: ");
				String solution = input.nextLine();// inputPargraph(input);
				que = new OpenEndedQuestion(text, solution, diff);
				isValid = addQuestion(repo, (OpenEndedQuestion)que);

			} else {
				que = new MultiSelectQuestion(text, diff);
				isValid = addQuestion(repo, (MultiSelectQuestion)que);
			}

			if (!isValid)
				System.out.println("An error occurred while creating the question! Please try again!");

		} while (!isValid);

	}
	
	private static boolean addQuestion(Repo repo, MultiSelectQuestion que) 
	{
		boolean res = repo.addQuestion(que);
		if(res) {
			for (Answer answer : que) {
				repo.addAnswer(answer);
			}
		}
		return res;
	}
	
	private static boolean addQuestion(Repo repo, OpenEndedQuestion que) 
	{

		boolean res = repo.addQuestion(que);
		if(res)
			repo.addAnswer(que.getSolution());
		return res;
	}

	/**
	 * Asks the user for an answers and adds it to the repo
	 * 
	 * @param repo the program's repository
	 */
	private static void addAnswer(Repo repo) {
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
	public static void hardCodedQue(Repo repo, Subject subject) {
		HashSet<MultiSelectQuestion> multiQuestions = new HashSet<>();
		HashSet<OpenEndedQuestion> openQuestions = new HashSet<>();

		if (subject == Subject.History) {
			// History MultiSelectQuestions:
            multiQuestions.add(new MultiSelectQuestion(
                    "What year did the Titanic sink in the Atlantic Ocean on 15 April, on its maiden voyage from Southampton?",
                    Difficulty.Moderate,
                    new Answer[]{
                            new Answer("1908", false),
                            new Answer("1912", true),
                            new Answer("1914", false),
                            new Answer("1920", false)
                    }));

            multiQuestions.add(new MultiSelectQuestion(
                    "Who was Prime Minister of Great Britain from 1841 to 1846?",
                    Difficulty.Moderate,
                    new Answer[]{
                            new Answer("William Gladstone", false),
                            new Answer("Benjamin Disraeli", false),
                            new Answer("Robert Peel", true),
                            new Answer("Lord Palmerston", false)
                    }));

            multiQuestions.add(new MultiSelectQuestion(
                    "Who was the first person in space?",
                    Difficulty.Easy,
                    new Answer[]{
                            new Answer("Yuri Gagarin", true),
                            new Answer("Neil Armstrong", false),
                            new Answer("John Glenn", false),
                            new Answer("Alan Shepard", false)
                    }));

            // History OpenEndedQuestions:
            openQuestions.add(new OpenEndedQuestion(
                    "What is the name of the first human to fly?",
                    "Orville Wright, United States",
                    Difficulty.Easy));

            openQuestions.add(new OpenEndedQuestion(
                    "What is the name of the first man on the moon?",
                    "Neil Armstrong, United States",
                    Difficulty.Easy));

            openQuestions.add(new OpenEndedQuestion(
                    "Which metal was discovered by Hans Christian Oersted in 1825?",
                    "Aluminium",
                    Difficulty.Hard));

        } else if (subject == Subject.Geography) {
            // Geography MultiSelectQuestions:
            multiQuestions.add(new MultiSelectQuestion(
                    "What is the name of the biggest technology company in South Korea?",
                    Difficulty.Easy,
                    new Answer[]{
                            new Answer("Samsung", true),
                            new Answer("LG", false),
                            new Answer("Hyundai", false),
                            new Answer("SK", false)
                    }));

            multiQuestions.add(new MultiSelectQuestion(
                    "What is the name of the largest desert in the world?",
                    Difficulty.Easy,
                    new Answer[]{
                            new Answer("Sahara", false),
                            new Answer("Gobi", false),
                            new Answer("Kalahari", false),
                            new Answer("Antarctic", true)
                    }));

            // Geography OpenEndedQuestions:
            openQuestions.add(new OpenEndedQuestion(
                    "What is the name of the current president of the United States?",
                    "Joe Biden, United States",
                    Difficulty.Easy));

            openQuestions.add(new OpenEndedQuestion(
                    "What is the capital of France?",
                    "Paris, France",
                    Difficulty.Easy));

            openQuestions.add(new OpenEndedQuestion(
                    "What is the name of the largest ocean in the world?",
                    "Pacific Ocean",
                    Difficulty.Easy));

            openQuestions.add(new OpenEndedQuestion(
                    "What is the name of the highest mountain in the world?",
                    "Mount Everest, Nepal",
                    Difficulty.Moderate));

            openQuestions.add(new OpenEndedQuestion(
                    "What is the name of the largest country in the world by area?",
                    "Russia",
                    Difficulty.Moderate));

		} else if (subject == Subject.Science) {

			 multiQuestions.add(new MultiSelectQuestion("What is the name of the largest bone in the human body?",
	                    Difficulty.Hard,
	                    new Answer[]{
	                            new Answer("Humerus", false),
	                            new Answer("Tibia", false),
	                            new Answer("Femur", true),
	                            new Answer("Pelvis", false)
	                    }));
	            multiQuestions.add(new MultiSelectQuestion("How many breaths does the human body take daily?",
	                    Difficulty.Hard,
	                    new Answer[]{
	                            new Answer("About 10,000", false),
	                            new Answer("About 20,000", true),
	                            new Answer("About 30,000", false),
	                            new Answer("About 50,000", false)
	                    }));
	            multiQuestions.add(new MultiSelectQuestion("What is the chemical symbol of Tungsten?",
	                    Difficulty.Easy,
	                    new Answer[]{
	                            new Answer("Tn", false),
	                            new Answer("Tu", false),
	                            new Answer("Tg", false),
	                            new Answer("W", true)
	                    }));
	            multiQuestions.add(new MultiSelectQuestion("What do you use to measure rainfall?",
	                    Difficulty.Moderate,
	                    new Answer[]{
	                            new Answer("Barometer", false),
	                            new Answer("Hygrometer", false),
	                            new Answer("Pluviometer", true),
	                            new Answer("Thermometer", false)
	                    }));
	            multiQuestions.add(new MultiSelectQuestion("Which is the smallest planet in our solar system?",
	                    Difficulty.Easy,
	                    new Answer[]{
	                            new Answer("Venus", false),
	                            new Answer("Mars", false),
	                            new Answer("Mercury", true),
	                            new Answer("Pluto", false)
	                    }));

	            // Science OpenEndedQuestions:

	            openQuestions.add(new OpenEndedQuestion("What is the name of the element with the atomic number 1?",
	                    "Hydrogen", Difficulty.Easy));
	            openQuestions.add(new OpenEndedQuestion("What is the name of the force that keeps the planets in orbit around the sun?",
	                    "Gravity", Difficulty.Moderate));
	            openQuestions.add(new OpenEndedQuestion("What is the name of the process by which plants make food from sunlight?",
	                    "Photosynthesis", Difficulty.Moderate));
	            
		}else if(subject == Subject.Math) {
			// Math OpenEndedQuestions:

            openQuestions.add(new OpenEndedQuestion("What is the equation for the area of a circle?",
                    "A = PI*(r^2)", Difficulty.Moderate));
            openQuestions.add(new OpenEndedQuestion("What is the equation for the volume of a sphere?",
                    "V = 4/3*PI*(r^3)", Difficulty.Moderate));
            openQuestions.add(new OpenEndedQuestion("What is the equation for the slope of a line?",
                    "m = (y2 - y1)/(x2 - x1)", Difficulty.Moderate));
            openQuestions.add(new OpenEndedQuestion("What is the equation for the Pythagorean Theorem?",
                    "a^2 + b^2 = c^2", Difficulty.Moderate));

            // Math MultiSelectQuestions:

            multiQuestions.add(new MultiSelectQuestion("Which of the following are prime numbers?",
                    Difficulty.Easy,
                    new Answer[]{
                            new Answer("2", true),
                            new Answer("1", false),
                            new Answer("21", false),
                            new Answer("4", false),
                            new Answer("11", true)
                    }));
            
            multiQuestions.add(new MultiSelectQuestion("What are the equations for linear functions?",
                    Difficulty.Easy,
                    new Answer[]{
                            new Answer("y = mx + b", true),
                            new Answer("y = x^2 + b", false),
                            new Answer("y = mx^2 + b", false),
                            new Answer("y = b^x", false)
                    }));
            
            multiQuestions.add(new MultiSelectQuestion("What is the derivative of the function f(x) = x^3?",
                    Difficulty.Hard,
                    new Answer[]{
                            new Answer("3x^2", true),
                            new Answer("x^2", false),
                            new Answer("3x", false),
            		}));
		}
		
		for (OpenEndedQuestion openEndedQuestion : openQuestions) {
			addQuestion(repo, openEndedQuestion);
		}
		
		for (MultiSelectQuestion multiSelectQuestion : multiQuestions) {
			addQuestion(repo, multiSelectQuestion);
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

	/**
	 * @param input where to read the difficulty from
	 * @return difficulty from the user
	 */
	private static Difficulty getDifficultyFromUser() {
		ArrayList<Difficulty> difficulties = new ArrayList<Difficulty>(Arrays.asList(Difficulty.values()));
		int diff = 0;
		boolean isValid = false;

		do {
			System.out.println("Select difficulty: ");
			for (Difficulty d : difficulties) {
				System.out.println((d.ordinal() + 1) + ". " + d.name());
			}
			diff = input.nextInt();
			input.nextLine(); // clears buffer
			diff--;

			isValid = diff < difficulties.size() && diff >= 0;
			if (!isValid)
				System.out.println("Error! Difficulty dosen't exist, Try again!");

		} while (!isValid);

		return difficulties.get(diff);

	}

	/**
	 * @param input where to read the subject from
	 * @return subject from the user
	 */
	private static Subject getSubjectFromUser() {
		ArrayList<Subject> subjects = new ArrayList<Subject>(Arrays.asList(Subject.values())); 
		int subject = 0;
		boolean isValid = false;

		do {
			
			//print the subject
			System.out.println("Select a subject: ");
			for (Subject d : subjects) {
				System.out.println((d.ordinal() + 1) + ". " + d.name());
			}
			
			subject = input.nextInt();
			input.nextLine(); // clears buffer
			subject--;//start from 0 instead of 1

			isValid = subject < subjects.size() && subject >= 0;
			if (!isValid)
				System.out.println("Error! Subject dosen't exist, Try again!");

		} while (!isValid);

		return subjects.get(subject);
	}
	
}
