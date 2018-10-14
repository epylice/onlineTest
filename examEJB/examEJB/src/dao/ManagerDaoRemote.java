package dao;

import javax.ejb.Remote;

@Remote
public interface ManagerDaoRemote {

	public String check(String name,String password);
}
