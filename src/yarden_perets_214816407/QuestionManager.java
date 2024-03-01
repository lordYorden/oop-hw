package yarden_perets_214816407;

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
