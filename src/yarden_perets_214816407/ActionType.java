package yarden_perets_214816407;

import java.util.Scanner;

public enum ActionType {
	
	PrintQuestions(1, "Display all of the question from the repo (and their answers)"), 
	AddAnswer(2, "Add a new answers to the repo"),
	AppendAnswerToQuestion(3, "Append an answer to an existing question"),
	AddQuestion(4, "Add a new question"),
	DeleteAnswerFromAQuestion(5, "Delete an answers to a question"),
	DeleteQuestion(6, "Delete a question"),
	GenerateTest(7, "Generate a new test"),
	Exit(-1, "to exit");
	
	private int value;
	private String description;

	ActionType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }
    

    @Override
    public String toString() {
        return String.format("Type: %s\nDescription: %s", this.name(), this.description);
    }

    public static ActionType getActionType(int value) {
        for(ActionType v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! Type dose not exist, Please try again!");
    }
    
    /**
	 * @param input where to read the subject from
	 * @return subject from the user
	 */
	public static ActionType getActionTypeFromUser(Scanner input) {
		while(true) {
			//print the QuestionType
			System.out.println("Please select an option: (-1 to exit)");
			for (ActionType d : ActionType.values()) {
				System.out.println(d.value + ". " + d.description);
			}
			
			int select = 0;
			ActionType actionType = null;
			select = input.nextInt();
			input.nextLine(); // clears buffer
			try {
				actionType = ActionType.getActionType(select);
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			return actionType;
		}
	}

}
