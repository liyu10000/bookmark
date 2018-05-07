package bookmark.dao;

import java.util.List;

import bookmark.DataStore;
import bookmark.entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
