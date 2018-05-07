package bookmark.dao;

import java.util.List;

import bookmark.DataStore;
import bookmark.entities.Bookmark;
import bookmark.entities.UserBookmark;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
}
