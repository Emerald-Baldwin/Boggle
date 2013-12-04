/*Name: Emerald Baldwin
  Assignment: Lab 2
  Title: Boggle
  Course: CSCE 270
  Lab Section: 2
  Semester: Fall 2013
  Instructor: Kenneth Blaha
  Date: 11/17/13
  Sources consulted: Dr. Blaha
  Program description: This runs a boggle game
  Known Bugs: won't run on my computer because I can't parse the words.txt file
  I really hope it runs on a computer that can parse the text file. I seriously
  spent all weekend trying to make a gui and am giving up now on the creativity
  Creativity: none. */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
	
	ArrayList<String> dict;
	
	/** Constructor
	  * @param textFile The name of the textfile to load
	  */
	public Dictionary(String textFile) throws FileNotFoundException {
		loadDict(textFile);
	}
	
	/* private method that loads the dictionary from the textfile specified
	 * by the call to the constructor 
	 * @param textfile The file to load 
	 */
	@SuppressWarnings("unchecked")
	private void loadDict(String textFile) throws FileNotFoundException {
		try {
			Scanner input = new Scanner(new File(textFile));
			dict = new ArrayList<String>(172823);
			while (input.hasNext()) {
				dict.add(input.nextLine());
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found: " + textFile);
		}
		
	}
	
	/** Method that searches the dictionary for a specified target
	  * @param target the word to search for in the dictionary
	  * @return returns the index of the word if found; otherwise, returns -1
	  */
	@SuppressWarnings("unchecked")
	public int search(String target) {
		return searchDict(dict, target);
	}
	
	/* private method that recursively searches the array using binary search
	 * @param items The array to search
	 * @param target The word to search for
	 * @param first the first item in our array
	 * @param last The last item in our array
	 * @return The index of the item, or -1 if the item cannot be found
	 */
	private int searchDict(ArrayList<String> items, String target, int first, int last) {
		if (first > last) {
			return -1; // base case for unsuccessful search
		}
		else {
			int middle = (first + last) / 2; // next probe index
			int compResult = target.compareTo(items.get(middle));
			if (compResult == 0){
				return middle; // base case for successful search
			} else if (compResult < 0) {
				return searchDict(items, target, first, middle - 1);
			} else {
				return searchDict(items, target, middle + 1, last);
			}
		}
	}
	
	/* private method that calls the recursive binary search method
	 * @param items The array to search
	 * @param target the word to search for
	 * @return the index of the word if found; otherwise -1
	 */
	private int searchDict(ArrayList<String> items, String target) {
		return searchDict(items, target, 0, items.size() - 1);
	}
}
