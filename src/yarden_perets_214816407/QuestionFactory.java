package yarden_perets_214816407;

public class QuestionFactory {
	public static Question createQuestion(QuestionType type, Difficulty difficulty, String text, String solution) {

		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			throw new IllegalArgumentException("Error! Can't register answers when Repo isn't loaded!");
		
		
		
		Question que = facade.doseQuestionExist(text);
		
		switch (type) {
		
		case MultiSelect:
			if(que == null || que instanceof OpenEndedQuestion) {
				que = new MultiSelectQuestion(text, difficulty);
			}
			break;
		case OpenEnded:
			if(que == null || que instanceof MultiSelectQuestion) {
				facade.addAnswer(AnswerFactory.createAnswer(solution, false));
				que = new OpenEndedQuestion(text, solution, difficulty);
			}
			break;
		default:
			throw new IllegalArgumentException("Error! No such type!");
		}
		
		if(!facade.addQuestion(que))
			throw new RuntimeException("An error occurred while creating the question! Please try again!");
		
		return que;
	}
	
	public static Question createQuestion(Question que) {
		if (que instanceof MultiSelectQuestion) {
			return new MultiSelectQuestion((MultiSelectQuestion) que);
		}else if(que instanceof OpenEndedQuestion) {
			return new OpenEndedQuestion((OpenEndedQuestion) que);
		}else {
			throw new IllegalArgumentException("Error! No such question!");
		}
	}
	
	public static MultiSelectQuestion createQuestionWithAnswers(QuestionType type, String text, Difficulty difficulty, Answer[] answers) {
		MultiSelectQuestion question = (MultiSelectQuestion) createQuestion(type, difficulty, text, null);
		for (Answer answer : answers) {
			question.addAnswer(answer);
		}
		return question;
	}
	
	public static OpenEndedQuestion createOpenEndedQuestion(QuestionType type, String text, String solution, Difficulty difficulty) {
		return (OpenEndedQuestion) createQuestion(type, difficulty, text, solution);
	}
}
