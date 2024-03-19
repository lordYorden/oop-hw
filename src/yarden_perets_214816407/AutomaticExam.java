package yarden_perets_214816407;

import java.util.HashSet;
import java.util.Random;

public class AutomaticExam extends Exam {

	private final static Random rnd = new Random();
	private HashSet<Answer> generated;
	
	public AutomaticExam(int maxNumQue, int numQuestions) throws NumOfQuestionsException {
		super(maxNumQue, numQuestions);
		this.generated = new HashSet<>();
	}
	
	public Answer generateAnswerFromRepo(boolean isCorrect, ElementManager<Answer> answers) {
		
		int genAnsId = rnd.nextInt(answers.size() - 2) + 1; // not include default answers
		Answer ans = answers.getElement(genAnsId);
		ans = AnswerFactory.createAnswer(ans);
		ans.setCorrect(isCorrect);
		return ans;	
	}

	@Override
	public Question getQuestion(ElementManager<Question> questions, ElementManager<Answer> answers) {
		//QuestionManager questionManager = repo.getQuestions();
		int maxRange = questions.size();
		
		int genQue = rnd.nextInt(maxRange);
		Question questionSelected = questions.getElement(genQue);
		if(questionSelected == null){
			return null;
		}
		
		Question toAdd = QuestionFactory.createQuestion(questionSelected);
		
		if (questionSelected instanceof MultiSelectQuestion) {
			MultiSelectQuestion multiGen = (MultiSelectQuestion) toAdd;//new MultiSelectQuestion((MultiSelectQuestion) questionSelected);
			multiGen.clear();

			// generate 4 answers
			int correctIndex = rnd.nextInt(MIN_ANSWERS_PER_QUESTION);
			generated = new HashSet<>();
			for (int j = 0; j < MIN_ANSWERS_PER_QUESTION; j++) {
				Answer ans = null;
				boolean wasGen = false;
				do {
					ans = generateAnswerFromRepo(j == correctIndex, answers);
					wasGen = generated.contains(ans);
					if(!wasGen) {
						generated.add(ans);
					}
				} while (wasGen);
				multiGen.addAnswer(ans);
			}
		}
		
		return toAdd;
	}

}
