// jUnit test for the file that loads the Boggle board, BoggleBoard.java

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BoggleBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = FileNotFoundException.class)
	public void testBoggleBoard() {
		// test a filename that works
		BoggleBoard myBoard = new BoggleBoard("board.txt");
		
		// test a filename that does not exist - should throw an exception
		BoggleBoard myBoard = new BoggleBoard("Idontexist.txt");
	}

	@Test
	public void testSearch() {
		BoggleBoard myBoard = new BoggleBoard("board.txt");
		
		// search the board for a word that is in the board
		assertEquals(true, myBoard.search("benthic"));
		
		// search the board for a word that is not in the board
		assertEquals(false, myBoard.search("water"));
		
		// search the board for a word that is almost in the board
		assertEquals(false, myBoard.search("benthica"));
	}

	@Test
	public void testGetTotalPoints() {
		BoggleBoard myBoard = new BoggleBoard("board.txt");
		myBoard.search("benthic"));
		myBoard.search("water"));
		myBoard.search("a");
		myBoard.search("hit"));
		myBoard.search("sect"));
		
		// assert that the total points we have recieved is 7
		assertEquals(7, myBoard.getTotalPoints());
	}

	@Test
	public void testGetRecentPoints() {
		BoggleBoard myBoard = new BoggleBoard("board.txt");
		myBoard.search("benthic"));
		// check that benthic gave us 5 points
		assertEquals(5, myBoard.getRecentPoints());
		
		// check that water gave us no points
		myBoard.search("water"));
		assertEquals(0, myBoard.getRecentPoints());
		
		// check that we get no points for a word under three characters long
		myBoard.search("a");
		assertEquals(0, myBoard.getRecentPoints());
	}

}
