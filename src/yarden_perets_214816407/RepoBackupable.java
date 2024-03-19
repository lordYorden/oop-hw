package yarden_perets_214816407;

public interface RepoBackupable {
	
	public Repo loadRepo(Subject subject) throws Exception;
	public void saveRepo(Repo repo) throws Exception;

}
