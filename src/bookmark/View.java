package bookmark;

import bookmark.constants.KidFriendlyStatus;
import bookmark.constants.UserType;
import bookmark.controllers.BookmarkController;
import bookmark.entities.Bookmark;
import bookmark.entities.User;
import bookmark.partner.Shareable;

public class View {

	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items...");
		int bookmarkCount = 0;

		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						bookmarkCount++;

						BookmarkController.getInstance().saveUserBookmark(user, bookmark);

						System.out.println("New item bookmarked..." + bookmark);
					}
				}

				
				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					// Mark as kid friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatusDecision = getKidFriendlyStatusDecision(bookmark);
						if (!kidFriendlyStatusDecision.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, bookmark, kidFriendlyStatusDecision);
						}
					}
					
					// Share bookmarks
					if (bookmark instanceof Shareable && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)) {
						boolean shareDecision = getShareDecision();
						if (shareDecision) {
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
		}

	}

	// TODO: below methods simulate user input. after IO, we take input via console.
	
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;
	}

	private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
		double random = Math.random();
		return random < 0.4 ? KidFriendlyStatus.APPROVED
				: ((random >= 0.4 && random < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN);
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}

	/*
	 * public static void bookmark(User user, Bookmark[][] bookmarks) {
	 * System.out.println("\n" + user.getEmail() + " is bookmarking"); for (int
	 * i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) { int typeOffset = (int)
	 * (Math.random() * DataStore.BOOKMARK_TYPES_COUNT); int bookmarkOffset =
	 * (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
	 * 
	 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	 * 
	 * System.out.println(bookmark); } }
	 */

}