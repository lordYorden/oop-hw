package yarden_perets_214816407;

import java.util.HashSet;
import java.util.Random;

public class AutomaticExam extends Exam {

	private final static Random rnd = new Random();
	private HashSet<Answer> generated;
	
	public AutomaticExam(int maxNumQue) throws NumOfQuestionsException {
		super(maxNumQue);
		this.generated = new HashSet<>();
	}

	@Override
	public void createExam(Repo repo) {
		int maxRange = repo.getNumQuestions();
		int genQue = 0;
		for (int i = 0; i < maxNumQue; i++) {
			
			genQue = rnd.nextInt(maxRange);
			Question questionSelected = repo.getQuestionByID(genQue);
			if (questionSelected instanceof OpenEndedQuestion) {

				try {
					this.addQuestion((Question) new OpenEndedQuestion((OpenEndedQuestion) questionSelected));
				} catch (NumOfQuestionsException e) {
					System.out.println("Error! " + e);
				}

			} else if (questionSelected instanceof MultiSelectQuestion) {
				MultiSelectQuestion multiGen = new MultiSelectQuestion((MultiSelectQuestion) questionSelected);
				multiGen.clear();

				// generate 4 answers
				int genAns = 0;
				int numAnswers = repo.getNumAnswers();

				int correctIndex = rnd.nextInt(4);
				generated = new HashSet<>();
				for (int j = 0; j < 4; j++) {
					Answer ans = null;
					boolean wasGen = false;
					do {
						genAns = rnd.nextInt(numAnswers - 2) + 1; // not include default answers
						ans = repo.getAnswerById(genAns);
						ans = new Answer(ans);
						ans.setCorrect(correctIndex == j);
						wasGen = generated.contains(ans);
						if(!wasGen) {
							generated.add(ans);
						}
					} while (wasGen);
					
					multiGen.addAnswer(ans);
				}
				
				try {
					this.addQuestion(multiGen, repo);
				} catch (NumOfAnswersException | NumOfQuestionsException e) {
					System.out.println("Error! " + e);
				}
			}
			
			if(!generated.isEmpty())
				generated.clear();

			
			if(questions.size() != i+1) 
			{
				i--;// didn't find question or duplicate
			}
		}

	}

	@SuppressWarnings("unused")
	@Deprecated
	private boolean wasGenerated(String[] generated, String question) {
		for (int i = 0; i < generated.length; i++) {
			if (generated[i] != null && generated[i].equals(question)) {
				return true;
			}
		}
		return false;
	}

}
