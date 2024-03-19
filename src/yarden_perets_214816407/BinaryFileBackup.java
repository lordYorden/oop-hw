package yarden_perets_214816407;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BinaryFileBackup implements RepoBackupable{

	@Override
	public Repo loadRepo(Subject subject) throws Exception {
		String filename = subject.name() + ".db";
		ObjectInputStream toLoad = new ObjectInputStream(new FileInputStream(filename));
		Repo repo = (Repo) toLoad.readObject();
		toLoad.close();
		Question.setNumQuestions(repo.getQuestions().size() + 1);
		return repo;
	}

	@Override
	public void saveRepo(Repo repo) throws Exception {
		String filename = repo.getSubject().name() + ".db";
		ObjectOutputStream toSave = new ObjectOutputStream(new FileOutputStream(filename));
		toSave.writeObject(repo);
		toSave.close();
	}

}
