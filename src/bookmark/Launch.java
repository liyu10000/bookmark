package bookmark;

import java.util.List;

import bookmark.bgjobs.WebpageDownloaderTask;
import bookmark.entities.Bookmark;
import bookmark.entities.User;
import bookmark.managers.BookmarkManager;
import bookmark.managers.UserManager;

public class Launch {

	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	
	private static void loadData() {
		System.out.println("1. loading data...");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
		
		//printUserData();
		//printBookmarkData();
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	private static void printBookmarkData() {
		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}
	
	private static void start() {
		//System.out.println("\n2. bookmarking...");
		for (User user : users) {
			View.browse(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		start();
		
		// Background Jobs
		runDownloaderJob();
	}
	
	private static void runDownloaderJob() {
		WebpageDownloaderTask task = new WebpageDownloaderTask(true);
		(new Thread(task)).start();
	}

}
