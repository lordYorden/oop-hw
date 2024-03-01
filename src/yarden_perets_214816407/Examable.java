package yarden_perets_214816407;

public interface Examable {
	public void createExam(Repo repo);
	public Question getQuestion(ElementManager<Question> questions, ElementManager<Answer> answers);
}
