package yarden_perets_214816407;

import java.util.Scanner;

public enum Difficulty {
	Easy(1), Moderate(2), Hard(3);
	
	private int value;

	Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public static Difficulty getSubject(int value) {
        for(Difficulty v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! Difficulty dosen't exist, Try again!");
    }
    
    /**
	 * @param input where to read the subject from
	 * @return subject from the user
	 */
	public static Difficulty getDifficultyFromUser(Scanner input) {
		while(true) {
			//print the subject
			System.out.println("Select a Difficulty: ");
			for (Difficulty d : Difficulty.values()) {
				System.out.println(d + ". " + d.name());
			}
			
			int select = 0;
			Difficulty subject;
			select = input.nextInt();
			input.nextLine(); // clears buffer
			try {
				 subject = Difficulty.getSubject(select);
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			return subject;
		}
	}
}
