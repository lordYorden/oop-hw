package yarden_perets_214816407;

import java.util.Scanner;

public enum Subject {
	Math(1), Science(2), History(3), Geography(4);
	
	private int value;

	Subject(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public static Subject getSubject(int value) {
        for(Subject v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! Subject dosen't exist, Try again!");
    }
    
    /**
	 * @param input where to read the subject from
	 * @return subject from the user
	 */
	public static Subject getSubjectFromUser(Scanner input) {
		while(true) {
			//print the subject
			System.out.println("Select a Subject: ");
			for (Subject d : Subject.values()) {
				System.out.println(d + ". " + d.name());
			}
			
			int select = 0;
			Subject subject;
			select = input.nextInt();
			input.nextLine(); // clears buffer
			try {
				 subject = Subject.getSubject(select);
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			return subject;
		}
	}
}
