package bookmark.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import bookmark.constants.BookGenre;
import bookmark.constants.KidFriendlyStatus;
import bookmark.constants.MovieGenre;
import bookmark.dao.BookmarkDao;
import bookmark.entities.Book;
import bookmark.entities.Bookmark;
import bookmark.entities.Movie;
import bookmark.entities.User;
import bookmark.entities.UserBookmark;
import bookmark.entities.WebLink;
import bookmark.util.HttpConnect;
import bookmark.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Book createBook(long id, String title, int publicationYear, String publisher,
			String[] authors, BookGenre genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;
	}

	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink webLink = new WebLink();
		webLink.setId(id);
		webLink.setTitle(title);
		webLink.setUrl(url);
		webLink.setHost(host);

		return webLink;
	}
	
	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		/*
		if (bookmark instanceof WebLink) {
			try {				
				String url = ((WebLink)bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((WebLink)bookmark).getUrl());
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, Bookmark bookmark, KidFriendlyStatus kidFriendlyStatusDecision) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatusDecision);
		bookmark.setKidFriednlyMarkedBy(user);
		dao.updateKidFriendlyStatus(bookmark);
		System.out.println("Kid-friendly status: " + kidFriendlyStatusDecision + ", " + bookmark);
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		
		System.out.println("Data to be shared: ");
		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getItemData());;
		} else if (bookmark instanceof WebLink) {
			System.out.println(((WebLink) bookmark).getItemData());;
		}
		
		dao.sharedByInfo(bookmark);
	}

}
