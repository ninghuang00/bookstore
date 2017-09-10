package cn.hn.dao;

import java.util.List;

import cn.hn.domain.Privilege;
import cn.hn.domain.User;

public interface UserDao {

	void add(User user);

	User find(String id);

	User find(String username, String password);

	void remove(String id);

	List<Privilege> getPrivileges(String id);

}