package yarden_perets_214816407;
import java.util.LinkedHashSet;

public class AnswerManager extends ElementManager<Answer>{
	
	//private LinkedHashSet<Answer> answers;
	private static final long serialVersionUID = Repo.REPO_VERSION;
	
	public AnswerManager() {
		super();
	}
	
	public AnswerManager(LinkedHashSet<Answer> answers) {
		super(answers);
	}
	
	public AnswerManager(ElementManager<Answer> answerManager) {
		this(answerManager.elements);
	}

	@Override
	public boolean addElement(Answer ansToAdd) {
		if(elements.contains(ansToAdd))
			return false;
		
		Answer newAns = new Answer(ansToAdd);
		return elements.add(newAns);
	}
	
	@Override
	public Answer getElement(int key) {		
		for(Answer curr : elements) {
			if(curr.compareTo(key) == 0)
				return curr;
		}
		return null;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
//		for(Answer answer : answers) {
//			builder.append(answer.toString());
//		}
		int i = 1;
		for (Answer answer : elements) {
			builder.append(i);//for a nice print
			builder.append(". ");
			builder.append(answer.toString());
			i++;
		}
		return builder.toString();
	}

	@Override
	public String getSolution() {
		StringBuilder builder = new StringBuilder();
		
//		for(Solutionable answer : answers) {
//			builder.append(answer.getSolution());
//		}
		builder.append("\n");

		int i = 1;
		for (Solutionable answer : elements) {
			builder.append(i);//for a nice print
			builder.append(". ");
			builder.append(answer.getSolution());
			i++;
		}
		return builder.toString();
	}
}
