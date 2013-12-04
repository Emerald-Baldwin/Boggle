/* Name: Emerald Baldwin
 * Assignment: Lab 8
 * Title: Boggle
 * Course: CSCE 270
 * Lab Section: 2
 * Semester: Fall 2013
 * Instructor: Kenneth Blaha
 * Date: 11/17/13
 * Sources Consulted: Dr. Blaha
 * Program Description: Driver for the Boggle Program, runs the entire Boggle game
 */
  
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Boggle {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		BoggleBoard mine = new BoggleBoard("board.txt");
		//BoggleGUI gui = new BoggleGUI();
		mine.drawBoard();
		
		Dictionary myDict = new Dictionary("words.txt");
		Scanner keyboard;
		keyboard = new Scanner(System.in);
		System.out.println("Please enter a word or type enter to stop: ");
		String input = keyboard.nextLine();
		while (!input.equals("")) {
			// check if word is in dictionary
			if (input.length() < 3) {
				System.out.println("The word " + input + " is too short!");
			} else if (myDict.search(input) == -1) {
				System.out.println("The word " + input + " is not a valid word (not in the dictionary).");
			} else if (myDict.search(input) != -1) {
				if (mine.search(input)) {
					int points = mine.getRecentPoints();
					System.out.println("The word " + input + " is good! You score " + points + " points!");
				} else {
					System.out.println("The word " + input + " is a valid word, but is not on the board.");
				}
			}
			
			mine.drawBoard();
			System.out.println("Please enter another word or type enter to stop: ");
			input = keyboard.nextLine();
		}
		int totalPoints = mine.getTotalPoints();
		System.out.println("You score " + totalPoints + " points!");
	}

}
