package dao;

import java.util.List;

import javax.ejb.Remote;

import bean.User;

@Remote
public interface UserDaoRemote {

	public void addUser(String username,String password,String telephone,String email);
	public List<User> showAll();
	public String nameIsExit(String name);
	public String check(String name,String password);
	public String checkxinxi(String name,String tele,String email);
	public String update(String name,String password);
	public String queryName(int number);
	public int queryNumber(String name);
}
