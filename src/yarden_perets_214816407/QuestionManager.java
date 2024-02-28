package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedHashSet;

public class QuestionManager implements Serializable, Solutionable{
	
	private LinkedHashSet<Question> questions;
	private static final long serialVersionUID = Repo.REPO_VERSION;
	
	public QuestionManager() {
		this.questions = new LinkedHashSet<>();
	}

	public boolean addQuestion(Question queToAdd) { 
		return questions.add(queToAdd);
	}
	
	public Question getQuestion(int key) {
		for (Question curr : questions) {
			if (curr.compareTo(key) == 0){
				return curr;
			}
		}
		return null;
	}
	
	//for other types like string
	public Question getQuestion(Object key, Comparator<Object> comparator) {
		for (Question curr : questions) {
			if (comparator.compare(curr, key) == 0)
				return curr;
		}
		return null;
	}
	
	
	public boolean deleteQuestion(int key) {
		if(questions.isEmpty())
			return false;
		return questions.remove(getQuestion(key));
	}
	
	public int getNumQuestions() {
		return questions.size();
	}
	
	public boolean isEmpty() {
		return questions.isEmpty();
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		for(Question question : questions) {
			builder.append(question.toString());
		}
		return builder.toString();
	}

	@Override
	public String getSolution() {
		StringBuilder builder = new StringBuilder();
		
		for(Solutionable question : questions) {
			builder.append(question.getSolution());
		}
		return builder.toString();
	}

	

}
