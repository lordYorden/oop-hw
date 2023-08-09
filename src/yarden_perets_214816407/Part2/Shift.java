//package yarden_perets_214816407.Part2; //for code not compling everything in comment
//
//import java.time.LocalDateTime;
//
//public class Shift {
//	private HairDresser[] assignedHairDressers;
//	private LocalDateTime start;
//	private LocalDateTime end;
//	private Treatments[] treatments;
//	
//	Shift(LocalDateTime start,LocalDateTime end, HairDresser[] assignedHairDressers, Treatments[] treatments){
//		this.start = start;
//		this.end = end;
//		this.assignedHairDressers = assignedHairDressers;
//		this.treatments = treatments;
//		sortTreatments();
//	}
//	
//	public int calcProfit() {
//		int sum = 0;
//		for (int i = 0; i < treatments.length; i++) {
//			sum = treatments[i].calcProfit(); //including coffee expenese
//		}
//		return sum;
//	}
//	
//	private void sortTreatments() {
//		int i, j;
//		int n = treatments.length;
//	    boolean swapped;
//	    for (i = 0; i < n - 1 && !swapped; i++) {
//	        swapped = false;
//	        for (j = 0; j < n - i - 1; j++) {
//	            if (treatments[j].getDateTime().compareTo(treatments[j + 1].getDateTime()) > 0) {
//	            	
//	            	Treatments temp = treatments[j];
//	            	treatments[j] = treatments[j + 1];
//	            	treatments[j + 1] = temp;
//	                swapped = true;
//	            }
//	        }
//	    }
//	}
//	
//}
