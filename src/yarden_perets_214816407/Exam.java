package yarden_perets_214816407;

import java.io.FileNotFoundException;

//Adapter
public abstract class Exam extends hw3_Exam{

	public Exam(int maxNumQue, int numQuestion) throws NumOfQuestionsException {
		super(maxNumQue, numQuestion);
	}
	
	public void createExam(Repo repo) {
		hw3_createExam(repo);
	}
	
	public String writeExam(boolean displaySolution) throws FileNotFoundException {
		return hw3_writeExam(displaySolution);
	}
	
	@Override
	public boolean equals(Object obj) {
		return hw3_equals(obj);
	}
	
	@Override
	public String toString() {
		return hw3_toString();
	}

}
