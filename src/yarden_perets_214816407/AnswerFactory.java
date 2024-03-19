package yarden_perets_214816407;
import java.lang.IllegalArgumentException;
public class AnswerFactory {

	public static Answer createAnswer(String text, boolean solution) {

		ExamMakerFacade facade = ExamMakerFacade.getInstance();
		if(!facade.isLoaded())
			throw new IllegalArgumentException("Error! Can't register answers when Repo isn't loaded!");
		
		Answer ans = facade.doseAnswerExist(text);
		if(ans == null) {
			ans = new Answer(text, solution);
			facade.addAnswer(ans);
		}
		
		return ans;
	}
	
	public static Answer createAnswer(Answer ans) {
		return new Answer(ans);
	}
}
