package yarden_perets_214816407;

public class MenuActionCompleteListener implements ActionCompleteObserver {

	public MenuActionCompleteListener() {
	}
	
	@Override
	public void update(ActionType type) {
		System.out.printf("\nAction finished!\n%s\n", type);
	}

}
