package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Manager;

@Stateless
public class ManagerDao implements ManagerDaoRemote{
	
	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	public ManagerDao(){
		
	}

	
	//验证用户名或密码是否正确
		public String check(String name,String password){
			Query q = entityManager.createQuery("select u from Manager u where u.name=:uname ");
			q.setParameter("uname", name);
			if(q.getResultList().size()>0){
				List results = q.getResultList();
				Manager manager = (Manager)results.get(0);
				String pass = manager.getPassword();
				if(password.equals(pass)){
					return "yes";
				}
				
			}
			return "no";
		}
	
}
