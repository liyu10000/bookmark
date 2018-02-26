package bookmark.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import bookmark.constants.BookGenre;
import bookmark.managers.BookmarkManager;

public class BookTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test 1: Philosophy
		Book book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3);
		boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("For Philosophy Genre -- must return false", isKidFriendlyEligible);
		
		// Test 2: Self help
		book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.SELF_HELP, 4.3);
		isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("For Self_help Genre -- must return false", isKidFriendlyEligible);
		
		// Test 3: others
		book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.ART, 4.3);
		isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertTrue("For Self_help Genre -- must return true", isKidFriendlyEligible);
		
	}

}
