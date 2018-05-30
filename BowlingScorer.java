import java.util.Scanner;
import java.util.*;

public class BowlingScorer {

	int rolls[] = new int[22];
	int totalScore = 0;
	int rollsIndex = 0;
	
	public static void main(String[] args) {
		
		BowlingScorer scorer = new BowlingScorer();
		int[] score = new int[22];
		int ballcounter = 1;
		int framecounter = 1;
		boolean extraBall = false;
		boolean validInput = false;
		
		//Create user Input here
		Scanner input = new Scanner(System.in);
		
		for(int i=0; i<21; i++) {
			
			while (validInput == false) {
				try {
					//prompt for user input
					System.out.println("Frame " + framecounter + " - Enter number of pins for ball number " + ballcounter);
					score[i] = input.nextInt();
					//check if frame total is greater than 10 pins
					if (framecounter < 10 && ballcounter == 2 && score[i] + score[i-1] > 10)
						{System.out.println("You cannot roll more than 10 pins per frame, please enter a new number");}
					//check if value is between 0 and 10
					else if (score[i] < 0 || score[i] > 10) 
						{System.out.println("Number of pins must be between 0 and 10, please enter a new number");}
					else {validInput = true;}
				} catch (InputMismatchException exception) {
					//catch non integer input errors
					System.out.println("Value entered is not valid, please enter a number between 0 and 10");
					input.next();
				}
			}
			
			//add extra balls for spare or strike in frame 10
			if (score[i] == 10 && isLastFrame(framecounter)) {extraBall = true;}
			else if (ballcounter == 2 && isLastFrame(framecounter) && score[i] + score[i-1] == 10) {extraBall = true;}
			
			//reset ball counter when a strike is rolled and no extra ball is needed, or increase ballcounter
			if (score[i] == 10 && extraBall == false) {ballcounter = 1; framecounter++;}
			else ballcounter++;
			
			//reset ball counter and increase frame if no extra ball
			if (ballcounter == 3 && extraBall == false) {ballcounter = 1; framecounter++;}
			
			//end game after 10th frame
			if (framecounter > 10 || (framecounter == 10 && ballcounter > 3)) {break;}
			
			//reset validInput
			validInput = false;
		}
		
		//end user input
		input.close();
		
		//start scoring
		scorer.setRolls(score);
		scorer.getScore();
		System.out.print("Total Score is " + scorer.totalScore);
		
		/* Code below for testing and troubleshooting scoring
		
		loop to check ball numbers and values 
		for(int i=0; i<21; i++) {System.out.println("\nBall number: " + i + " roll value: " + score[i]);}
		
		testing scores
		
		scorer.testPerfectGame();  //should be 300
		scorer.testNoMarksGame();  // should be 20
		scorer.testNoPinsGame();  //should be 0
		scorer.testLastFrameSpare();  //should be 11
		scorer.testLastFrameStrike(); //should be 12
		scorer.getScore();
		System.out.print("Total Score is " + scorer.totalScore);
		
		*/
	}

	public int getScore(){
		
		for (int frame = 0; frame < 10; frame++){
			//check for strike
			if (checkStrike(rollsIndex)) {
				totalScore += rolls[rollsIndex] + rolls[rollsIndex + 1] + rolls[rollsIndex + 2];
				rollsIndex++;
			}
			//check for spare
			else if (checkSpare(rollsIndex)) {
				totalScore += rolls[rollsIndex] + rolls[rollsIndex + 1] + rolls[rollsIndex + 2];
				rollsIndex +=2;
			}
			//open frame
			else {totalScore += rolls[rollsIndex] + rolls[rollsIndex + 1];
			rollsIndex +=2;
			}
		}
		return totalScore;
	
	}

	private static boolean isLastFrame(int frame) {
		return frame == 10;
	}
	
	private boolean checkSpare(int rollsIndex){
		return rolls[rollsIndex] + rolls[rollsIndex + 1] == 10;		
	}

	private boolean checkStrike(int rollsIndex){
		return (rolls[rollsIndex] == 10);
	}
	
	public void setRolls(int[] values) {
			rolls = values;
	}
	
	//tests for scoring
	
	public void testPerfectGame() {
		int[] test = {10,10,10,10,10,10,10,10,10,10,10,10};
		setRolls(test);
	}
	
	public void testNoMarksGame() {
		int[] test = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		setRolls(test);
	}
	
	public void testNoPinsGame() {
		int[] test = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		setRolls(test);
	}
	
	public void testLastFrameStrike() {
		int[] test = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1,1};
		setRolls(test);
	}
	
	public void testLastFrameSpare() {
		int[] test = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,1,1,0};
		setRolls(test);
	}
	
}
