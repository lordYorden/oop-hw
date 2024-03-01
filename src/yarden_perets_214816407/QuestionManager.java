package yarden_perets_214816407;
import java.util.Comparator;

public class QuestionManager extends ElementManager<Question>{
	
	//private LinkedHashSet<Question> questions;
	private static final long serialVersionUID = Repo.REPO_VERSION;
	
	public QuestionManager() {
		super();
	}

	public boolean addElement(Question queToAdd) { 
		return elements.add(queToAdd);
	}
	
	public Question getElement(int key) {
		for (Question curr : elements) {
			if (curr.compareTo(key) == 0){
				return curr;
			}
		}
		return null;
	}
	
	@Deprecated
	//for other types like string
	public Question getElement(Object key, Comparator<Object> comparator) {
		for (Question curr : elements) {
			if (comparator.compare(curr, key) == 0)
				return curr;
		}
		return null;
	}
	
	@Deprecated
	public boolean deleteQuestion(int key) {
		if(elements.isEmpty())
			return false;
		return elements.remove(getElement(key));
	}
	
	@Deprecated
	public int getNumQuestions() {
		return elements.size();
	}
	
//	@Override
//	public String toString(){
//		StringBuilder builder = new StringBuilder();
//		
//		for(Question question : elements) {
//			builder.append(question.toString());
//		}
//		return builder.toString();
//	}

	@Override
	public String getSolution() {
		StringBuilder builder = new StringBuilder();
		
		for(Solutionable question : elements) {
			builder.append(question.getSolution());
		}
		return builder.toString();
	}

	

}
