/* Name: Emerald Baldwin
  Assignment: Lab 8
  Title: BoggleBoard
  Course: CSCE 270
  Lab Section: 2
  Semester: Fall 2013
  Instructor: Kenneth Blaha
  Date: 11/15/13
  Sources consulted: Dr. Blaha
  Program description: This program reads in a file and creates a boggle board from it
  It then uses a recursive search to find if a word is in the boggle board
  */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BoggleBoard {
	
	char[][] board; // 2-d array containing the letters in our board
	char[][] visited; // 2-d array specifying the places on our board that we have visited
	int recentPoints; // the points from the most recently inputed word
	int totalPoints = 0; // the total points for the whole game
	
	/**
	 * Constructor
	 * @param fileName the name of the file to be loaded
	 */
	public BoggleBoard(String fileName) {
		loadBoard(fileName);
	}
	
	/**
	 * private method that loads the board using the inputed filename
	 * @param fileName the name of the file to use
	 */
	private void loadBoard(String fileName) {
		try {
			Scanner input = new Scanner(new File(fileName));
			int size = input.nextInt();
			board = new char[size][size];
			visited = new char[size][size];
			input.nextLine();
			String[] temp = (input.nextLine()).split("\\s+");
			int num = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					board[i][j] = temp[num].charAt(0);
					visited[i][j] = ' ';
					num++;
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found: " + fileName);
		}
	}
	
	/**
	 * Draws the board
	 */
	public void drawBoard() {
		System.out.println("+-------+");
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board[0].length; j++) {
				if (j == board[0].length - 1) {
					System.out.print(board[i][j]);
				} else {
					System.out.print(board[i][j] + " ");
				}
			}
			System.out.println("|");
		}
		System.out.println("+-------+");
	}
	
	/**
	 * This is the public method that calls the private search method
	 * @param s The string to search
	 * @return true if the string was found
	 */
	public boolean search(String s) {
		// the letters in the board are all upper case
		s = s.toUpperCase();
		
		// search through the board for the first occurence of the letter
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == s.charAt(0)) {
					// if we find the whole word, we add points
					if (searchBoard(s, i, j)) {
						addPoints(s);
						// after finding the word, unmark the board
						for (int k = 0; k < board.length; k++) {
							for (int m = 0; m < board[0].length; m++){
								visited[k][m] = ' ';
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * This is a private method  that recursively searches a 2-d array (our board) for a string
	 * Returns a true if the word can be found in the array
	 * @param s the string to search for in the array
	 * @param row the current row that we are in in the array
	 * @param col the current columns that we are in in the array
	 * @return whether or not we found the letter at the column and row inputed
	 */
	private boolean searchBoard(String s, int row, int col) {
		// if the length is 0, we found all of the word in the board
		if (s.length() == 0) {
			return true;
		}
		// ensure that we aren't searching out of bounds
		if (row > board.length - 1 || col > board[0].length - 1 || row < 0 || col < 0) {
			return false;
		}
		// we don't to search squares we have already searched, so we mark
		// each square we come across
		if (visited[row][col] != '*') {
			visited[row][col] = '*';
			if (board[row][col] == s.charAt(0)) {
				if (searchBoard(s.substring(1), row + 1, col)) {
					return true;
				}
				if (searchBoard(s.substring(1), row, col + 1)) {
					return true;
				}
				if (searchBoard(s.substring(1), row + 1, col + 1)) {
					return true;
				}
				if (searchBoard(s.substring(1), row - 1, col)) {
					return true;
				}
				if (searchBoard(s.substring(1), row - 1, col - 1)) {
					return true;
				}
				if (searchBoard(s.substring(1), row, col - 1)) {
					return true;
				}
				if (searchBoard(s.substring(1), row + 1, col - 1)) {
					return true;
				}
				if (searchBoard(s.substring(1), row - 1, col + 1)) {
					return true;
				}
				visited[row][col] = ' ';
				return false;
			}
			// if we can't find the letter near our square, we unmark
			// the square we are in
			visited[row][col] = ' ';
			return false;
		}
		return false;
	}
	
	
	/**
	 * This method determines the points to be assigned
	 * depending on the length of the word
	 * @param s the word to be evaluated
	 */
	private void addPoints(String s) {
		if (s.length() == 3 || s.length() == 4) {
			recentPoints = 1;
		}
		if (s.length() == 5) {
			recentPoints = 2;
		}
		if (s.length() == 6) {
			recentPoints = 3;
		}
		if (s.length() == 7) {
			recentPoints = 5;
		}
		if (s.length() >= 8) {
			recentPoints = 11;
		}
		totalPoints += recentPoints;
	}
	
	/**
	 * getter method that returns the total points
	 * @return the total points earned in the game
	 */
	public int getTotalPoints() {
		return totalPoints;
	}
	
	/**
	 * getter method that returns the points earned from the most recent word
	 * @return point value of the most recent word
	 */
	public int getRecentPoints() {
		return recentPoints;
	}
}
