package bookmark.dao;

import bookmark.DataStore;
import bookmark.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
