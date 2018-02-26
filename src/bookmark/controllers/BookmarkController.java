package bookmark.controllers;

import bookmark.entities.Bookmark;
import bookmark.entities.User;
import bookmark.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
	}

	public void setKidFriendlyStatus(User user, Bookmark bookmark, String kidFriendlyStatusDecision) {
		BookmarkManager.getInstance().setKidFriendlyStatus(user, bookmark, kidFriendlyStatusDecision);
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().share(user, bookmark);
	}
}
