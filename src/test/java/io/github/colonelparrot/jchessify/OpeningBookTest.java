package io.github.colonelparrot.jchessify;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests whether the OpeningBook reads from built-in opening book
 * 
 * @version 1.0.2
 */
public class OpeningBookTest {
	private OpeningBook openingBook;
	
	/**
	 * Create the opening book and set it to read from default opening book 
	 */
	@Before
	public void setUp() {
		final String defaultOpeningBookPath = getClass().getClassLoader().getResource("games.txt").getFile();
		openingBook = new OpeningBook(defaultOpeningBookPath);
	}

	/**
	 * Close the opening book
	 */
	@After
	public void tearDown() {
		openingBook.close();
	}

	/**
	 * Tests whether it finds the right move from the book
	 */
	@Test
	public void test() {
		String bestMove = openingBook.findNextMove("e4 e5");
		assertEquals(bestMove, "Nf3");
	}

}
