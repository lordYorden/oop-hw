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
	
	public Answer generateAnswerFromRepo(boolean isCorrect, Repo repo) {
		int genAnsId = rnd.nextInt(repo.getNumAnswers() - 2) + 1; // not include default answers
		Answer ans = repo.getAnswerById(genAnsId);
		ans = new Answer(ans);
		ans.setCorrect(isCorrect);
		return ans;	
	}

	@Override
	public Question getQuestion(Repo repo) {
		QuestionManager questionManager = repo.getQuestions();
		int maxRange = questionManager.getNumQuestions();
		
		int genQue = rnd.nextInt(maxRange);
		Question questionSelected = questionManager.getQuestion(genQue);
		if(questionSelected == null){
			return null;
		}
		
		if (questionSelected instanceof MultiSelectQuestion) {
			MultiSelectQuestion multiGen = new MultiSelectQuestion((MultiSelectQuestion) questionSelected);
			multiGen.clear();

			// generate 4 answers
			int correctIndex = rnd.nextInt(MIN_ANSWERS_PER_QUESTION);
			generated = new HashSet<>();
			for (int j = 0; j < MIN_ANSWERS_PER_QUESTION; j++) {
				Answer ans = null;
				boolean wasGen = false;
				do {
					ans = generateAnswerFromRepo(j == correctIndex, repo);
					wasGen = generated.contains(ans);
					if(!wasGen) {
						generated.add(ans);
					}
				} while (wasGen);
				multiGen.addAnswer(ans);
			}
			
			return multiGen; //new Multi select
		}
		
		return (Question) new OpenEndedQuestion((OpenEndedQuestion) questionSelected); //new open ended
	}

}
