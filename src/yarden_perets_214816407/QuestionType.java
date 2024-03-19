package yarden_perets_214816407;

import java.util.Scanner;

public enum QuestionType {
	
	MultiSelect(1), OpenEnded(2);
	
	private int value;

	QuestionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public static QuestionType getQuestionType(int value) {
        for(QuestionType v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! Type dosen't exist, Try again!");
    }
    
    /**
	 * @param input where to read the subject from
	 * @return subject from the user
	 */
	public static QuestionType getQuestionTypeFromUser(Scanner input) {
		while(true) {
			//print the QuestionType
			System.out.println("Select a question type: ");
			for (QuestionType d : QuestionType.values()) {
				System.out.println(d + ". " + d.name());
			}
			
			int select = 0;
			QuestionType questionType = null;
			select = input.nextInt();
			input.nextLine(); // clears buffer
			try {
				questionType = QuestionType.getQuestionType(select);
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			return questionType;
		}
	}

}
