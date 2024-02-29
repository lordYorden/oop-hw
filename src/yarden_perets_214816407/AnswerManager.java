package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class AnswerManager implements Serializable, Solutionable, Iterable<Answer>{
	
	private LinkedHashSet<Answer> answers;
	private static final long serialVersionUID = Repo.REPO_VERSION;
	
	public AnswerManager() {
		this.answers = new LinkedHashSet<>();
	}
	
	public AnswerManager(LinkedHashSet<Answer> answers) {
		this.answers = new LinkedHashSet<>(answers);
	}
	
	public AnswerManager(AnswerManager answerManager) {
		this(answerManager.answers);
	}

	public boolean addAnswer(Answer ansToAdd) {
		Answer newAns = new Answer(ansToAdd);
		return answers.add(newAns);
	}

//	public boolean addAnswer(String ansToAdd) {	
//		Answer newAns = new Answer(ansToAdd, false); //Default
//		newAns.setId(answers.size());
//		return answers.add(newAns);
//	}
	
	public Answer getAnswer(int id) {		
		for(Answer curr : answers) {
			if(curr.compareTo(id) == 0)
				return curr;
		}
		return null;
	}
	
	public int size() {
		return answers.size();
	}
	
	//for other types like string
	public Answer getQuestion(Object key, Comparator<Object> comparator) {
		for (Answer curr : answers) {
			if (comparator.compare(curr, key) == 0)
				return curr;
		}
		return null;
	}
	
	//no delete in answers
	public boolean deleteQuestion(int key) {
		if(answers.isEmpty())
			return false;
		return answers.remove(getAnswer(key));
	}
		
	public boolean isEmpty() {
		return answers.isEmpty();
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
//		for(Answer answer : answers) {
//			builder.append(answer.toString());
//		}
		int i = 1;
		for (Answer answer : answers) {
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
		for (Solutionable answer : answers) {
			builder.append(i);//for a nice print
			builder.append(". ");
			builder.append(answer.getSolution());
			i++;
		}
		return builder.toString();
	}
	
	public void clear() {
		answers.clear();
	}
	
	@Override
	public Iterator<Answer> iterator() {
		return answers.iterator();
	}
}
