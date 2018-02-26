package bookmark.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import bookmark.constants.MovieGenre;
import bookmark.managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test 1: Horror
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles", "Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.HORROR, 8.5);
		boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("For Horror Genre -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 2: Thrillers
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles", "Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.THRILLERS, 8.5);
		isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("For Horror Genre -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 3: others
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles", "Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.CLASSICS, 8.5);
		isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertTrue("For Horror Genre -- isKidFriendlyEligible() must return true", isKidFriendlyEligible);
		
	}

}
