package yarden_perets_214816407;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExamMakerFacade {
	
	private Repo repo;
	private RepoBackupable repoBackup; 
	private static final Scanner input = new Scanner(System.in); 
	private static ExamMakerFacade _instance = null;
	public static final int MAX_QUESTION_CAPACITY = 10;
	public static final int MIN_ANSWERS_PER_QUESTION = 4;
	
	public static ExamMakerFacade getInstance() {
		if (_instance == null) {
            _instance = new ExamMakerFacade();
        }
        return _instance;
	}
	
	public void setRepoBackup(RepoBackupable repoBackup) {
		this.repoBackup = repoBackup;
	}

	private ExamMakerFacade(RepoBackupable repoBackupable) {
		this.repo = null;
		this.repoBackup = repoBackupable;
	}
	
	private ExamMakerFacade() {
		this(new BinaryFileBackup());
	}
	
	public boolean isLoaded() {
		return this.repo != null;
	}
	
	public void load(Subject subject) {
		try {
			this.repo = repoBackup.loadRepo(subject);
		} catch (Exception e) {
			this.repo = new Repo(subject);
			//default answers
			AnswerFactory.createAnswer("No answer is correct", false); // answers[0]
			AnswerFactory.createAnswer("More then one answer is correct", false); // answers[1]
			//works even by subject enjoy :)
			hardCodedQue(subject);
		}
	}
	
	public boolean save() {
		try {
			repoBackup.saveRepo(repo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public String generateTest(int numQue, boolean isAuto) throws NumOfQuestionsException, FileNotFoundException{
		Exam exam = null;

		int numQuestions = repo.getQuestions().size();
		if (isAuto) {
			exam = new AutomaticExam(numQue, numQuestions);
		} else {
			exam = new MenualExam(numQue, numQuestions, input);
		}

		exam.createExam(repo);

		exam.writeExam(true); // write solution
		String examPath = exam.writeExam(false); // write exam
		return examPath;
	}
	
	/**
	 * gets a question and then deletes it from the repo
	 * 
	 */
	public boolean deleteQuestion(int id) {
		return repo.getQuestions().deleteElement(id);
	}
	
	public void appendAnswerToQuestion(Question que, Answer ans, boolean isCorrect) {

		if (!(que instanceof MultiSelectQuestion)) {
			throw new IllegalArgumentException("Error! That's not a Multi Select Question, Can't add answers!");
		}
		
		AnswerFactory.createAnswer(ans);
		ans.setCorrect(isCorrect);

		boolean res = ((MultiSelectQuestion) que).addAnswer(ans);

		if (!res)
			throw new RuntimeException("Error! An Error aacured while adding the question! Might be duplicate answer or Full Capcity reached!");

	}
	
	/**
	 * Asks the user for a question and adds it to the repo
	 *
	 */
	public boolean addQuestion(Question que) {
		return repo.getQuestions().addElement(que);
	}
	
//	private static boolean addQuestion(Repo repo, MultiSelectQuestion que) 
//	{			
//		boolean res = repo.getQuestions().addElement(que);
//		if(res) {
//			for (Answer answer : que.getAnswers()) {
//				repo.addAnswer(answer);
//			}
//		}
//		return res;
//	}
	
//	private static boolean addQuestion(Repo repo, OpenEndedQuestion que) 
//	{
//
//		boolean res = repo.getQuestions().addElement(que);
//		if(res)
//			repo.addAnswer(que.getAnswer());
//		return res;
//	}

	/**
	 * Asks the user for an answers and adds it to the repo
	 * @param ans 
	 * 
	 */
	public void addAnswer(Answer ans) {
		repo.addAnswer(ans);

	}
	
	/**
	 * Adds the hard codded questions to the repo
	 * 
	 * @param repo the program's repository
	 */
	public static void hardCodedQue(Subject subject) {
		Set<MultiSelectQuestion> multiQuestions = new HashSet<>();
		Set<OpenEndedQuestion> openQuestions = new HashSet<>();
		QuestionType type = null;

		if (subject == Subject.History) {
			// History MultiSelectQuestions:
			type = QuestionType.MultiSelect;
            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,
                    "What year did the Titanic sink in the Atlantic Ocean on 15 April, on its maiden voyage from Southampton?",
                    Difficulty.Moderate,
                    new Answer[]{
                            AnswerFactory.createAnswer("1908", false),
                            AnswerFactory.createAnswer("1912", true),
                            AnswerFactory.createAnswer("1914", false),
                            AnswerFactory.createAnswer("1920", false)
                    }));

            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,
                    "Who was Prime Minister of Great Britain from 1841 to 1846?",
                    Difficulty.Moderate,
                    new Answer[]{
                            AnswerFactory.createAnswer("William Gladstone", false),
                            AnswerFactory.createAnswer("Benjamin Disraeli", false),
                            AnswerFactory.createAnswer("Robert Peel", true),
                            AnswerFactory.createAnswer("Lord Palmerston", false)
                    }));

            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,
                    "Who was the first person in space?",
                    Difficulty.Easy,
                    new Answer[]{
                            AnswerFactory.createAnswer("Yuri Gagarin", true),
                            AnswerFactory.createAnswer("Neil Armstrong", false),
                            AnswerFactory.createAnswer("John Glenn", false),
                            AnswerFactory.createAnswer("Alan Shepard", false)
                    }));

            // History OpenEndedQuestions:
            type = QuestionType.OpenEnded;
            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "What is the name of the first human to fly?",
                    "Orville Wright, United States",
                    Difficulty.Easy));

            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "What is the name of the first man on the moon?",
                    "Neil Armstrong, United States",
                    Difficulty.Easy));

            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "Which metal was discovered by Hans Christian Oersted in 1825?",
                    "Aluminium",
                    Difficulty.Hard));

        } else if (subject == Subject.Geography) {
            // Geography MultiSelectQuestions:
        	type = QuestionType.MultiSelect;
            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,
                    "What is the name of the biggest technology company in South Korea?",
                    Difficulty.Easy,
                    new Answer[]{
                            AnswerFactory.createAnswer("Samsung", true),
                            AnswerFactory.createAnswer("LG", false),
                            AnswerFactory.createAnswer("Hyundai", false),
                            AnswerFactory.createAnswer("SK", false)
                    }));

            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,
                    "What is the name of the largest desert in the world?",
                    Difficulty.Easy,
                    new Answer[]{
                            AnswerFactory.createAnswer("Sahara", false),
                            AnswerFactory.createAnswer("Gobi", false),
                            AnswerFactory.createAnswer("Kalahari", false),
                            AnswerFactory.createAnswer("Antarctic", true)
                    }));

            // Geography OpenEndedQuestions:
            type = QuestionType.OpenEnded;
            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "What is the name of the current president of the United States?",
                    "Joe Biden, United States",
                    Difficulty.Easy));

            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "What is the capital of France?",
                    "Paris, France",
                    Difficulty.Easy));

            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "What is the name of the largest ocean in the world?",
                    "Pacific Ocean",
                    Difficulty.Easy));

            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "What is the name of the highest mountain in the world?",
                    "Mount Everest, Nepal",
                    Difficulty.Moderate));

            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,
                    "What is the name of the largest country in the world by area?",
                    "Russia",
                    Difficulty.Moderate));

		} else if (subject == Subject.Science) {
			type = QuestionType.MultiSelect;
			multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"What is the name of the largest bone in the human body?",
			        Difficulty.Hard,
			        new Answer[]{
			                AnswerFactory.createAnswer("Humerus", false),
			                AnswerFactory.createAnswer("Tibia", false),
			                AnswerFactory.createAnswer("Femur", true),
			                AnswerFactory.createAnswer("Pelvis", false)
			        }));
			multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"How many breaths does the human body take daily?",
			        Difficulty.Hard,
			        new Answer[]{
			                AnswerFactory.createAnswer("About 10,000", false),
			                AnswerFactory.createAnswer("About 20,000", true),
			                AnswerFactory.createAnswer("About 30,000", false),
			                AnswerFactory.createAnswer("About 50,000", false)
			        }));
			multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"What is the chemical symbol of Tungsten?",
			        Difficulty.Easy,
			        new Answer[]{
			                AnswerFactory.createAnswer("Tn", false),
			                AnswerFactory.createAnswer("Tu", false),
			                AnswerFactory.createAnswer("Tg", false),
			                AnswerFactory.createAnswer("W", true)
			        }));
			multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"What do you use to measure rainfall?",
			        Difficulty.Moderate,
			        new Answer[]{
			                AnswerFactory.createAnswer("Barometer", false),
			                AnswerFactory.createAnswer("Hygrometer", false),
			                AnswerFactory.createAnswer("Pluviometer", true),
			                AnswerFactory.createAnswer("Thermometer", false)
			        }));
			multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"Which is the smallest planet in our solar system?",
			        Difficulty.Easy,
			        new Answer[]{
			                AnswerFactory.createAnswer("Venus", false),
			                AnswerFactory.createAnswer("Mars", false),
			                AnswerFactory.createAnswer("Mercury", true),
			                AnswerFactory.createAnswer("Pluto", false)
			        }));

	            // Science OpenEndedQuestions:
	            type = QuestionType.OpenEnded;
	            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,"What is the name of the element with the atomic number 1?",
	                    "Hydrogen", Difficulty.Easy));
	            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,"What is the name of the force that keeps the planets in orbit around the sun?",
	                    "Gravity", Difficulty.Moderate));
	            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,"What is the name of the process by which plants make food from sunlight?",
	                    "Photosynthesis", Difficulty.Moderate));
	            
		}else if(subject == Subject.Math) {
			// Math OpenEndedQuestions:
			type = QuestionType.OpenEnded;
            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,"What is the equation for the area of a circle?",
                    "A = PI*(r^2)", Difficulty.Moderate));
            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,"What is the equation for the volume of a sphere?",
                    "V = 4/3*PI*(r^3)", Difficulty.Moderate));
            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,"What is the equation for the slope of a line?",
                    "m = (y2 - y1)/(x2 - x1)", Difficulty.Moderate));
            openQuestions.add(QuestionFactory.createOpenEndedQuestion(type,"What is the equation for the Pythagorean Theorem?",
                    "a^2 + b^2 = c^2", Difficulty.Moderate));

            // Math MultiSelectQuestions:
            type = QuestionType.MultiSelect;
            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"Which of the following are prime numbers?",
                    Difficulty.Easy,
                    new Answer[]{
                            AnswerFactory.createAnswer("2", true),
                            AnswerFactory.createAnswer("1", false),
                            AnswerFactory.createAnswer("21", false),
                            AnswerFactory.createAnswer("4", false),
                            AnswerFactory.createAnswer("11", true)
                    }));
            
            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"What are the equations for linear functions?",
                    Difficulty.Easy,
                    new Answer[]{
                            AnswerFactory.createAnswer("y = mx + b", true),
                            AnswerFactory.createAnswer("y = x^2 + b", false),
                            AnswerFactory.createAnswer("y = mx^2 + b", false),
                            AnswerFactory.createAnswer("y = b^x", false)
                    }));
            
            multiQuestions.add(QuestionFactory.createQuestionWithAnswers(type,"What is the derivative of the function f(x) = x^3?",
                    Difficulty.Hard,
                    new Answer[]{
                            AnswerFactory.createAnswer("3x^2", true),
                            AnswerFactory.createAnswer("x^2", false),
                            AnswerFactory.createAnswer("3x", false),
            		}));
		}
	}
	
	public boolean isQuestionsEmpty() {
		return repo.getQuestions().isEmpty();
	}

	@Override
	public String toString() {
		return repo.toString();
	}

	public Question selectQuestion() {
		return Repo.selectQuestionFromRepo(repo.getQuestions(), input);
	}
	
	public Answer doseAnswerExist(String text) {
		return repo.getAnswers().getElement(text, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof String && o2 instanceof Answer) {
					String text = (String)o1;
					String answerText = ((Answer)o2).getText();
					return text.compareTo(answerText);
				}
				return -1;
			}}); 
	} 
	
	public Question doseQuestionExist(String text) {
		return repo.getQuestions().getElement(text, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof String && o2 instanceof Question) {
					String text = (String)o1;
					String questionText = ((Question)o2).getText();
					return text.compareTo(questionText);
				}
				return -1;
			}}); 
	} 
	
	public Answer selectAnswer() {
		return Repo.selectAnswerFromRepo(repo.getAnswers(), input);
	}
	
	public void removeAnswersFromQuestion(Question que) {
		
		if (!(que instanceof MultiSelectQuestion)) {
			throw new IllegalArgumentException("Error! Can't remove answers from a non Amercian Question!");
		}
		
		try {
			MultiSelectQuestion.deleteAnswersFromAQuestion((MultiSelectQuestion) que, input);
		} catch (NumOfAnswersException e) {
			System.out.println("Error! " + e.getMessage());
		}
	}
}
