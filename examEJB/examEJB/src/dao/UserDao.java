package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.User;

@Stateless
public class UserDao implements UserDaoRemote{

	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	public UserDao(){
		
	}
	
	//添加用户
	public void addUser(String username,String password,String telephone,String email){
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setTelephone(telephone);

		
		entityManager.persist(user);
	}
	
	//显示所有已注册用户
	public List<User> showAll(){
		Query q = entityManager.createQuery("from User u");
		List results = q.getResultList();
		return results;
		/*Iterator<User> it = results.iterator();
		while(it.hasNext()){
			User user  = (User)it.next();
			
		}*/
	}
	
	public String nameIsExit(String name){
		
		Query q = entityManager.createQuery("select u from User u where u.username=:uname ");
		q.setParameter("uname", name);
		if(q.getResultList().size()>0){
			return "yes";
		}
		return "no";
	}
	
	//验证用户名或密码是否正确
	public String check(String name,String password){
		Query q = entityManager.createQuery("select u from User u where u.username=:uname ");
		q.setParameter("uname", name);
		if(q.getResultList().size()>0){
			List results = q.getResultList();
			User user = (User)results.get(0);
			String pass = user.getPassword();
			if(password.equals(pass)){
				return "yes";
			}
			
		}
		return "no";
	}
	
	//验证资料
		public String checkxinxi(String name,String tele,String email){
			Query q = entityManager.createQuery("select u from User u where u.username=:uname ");
			q.setParameter("uname", name);
			String str = "";
			if(q.getResultList().size()>0){
				List results = q.getResultList();
				User user = (User)results.get(0);
				String telephone = user.getTelephone();
				String email2 = user.getEmail();
				if(!telephone.equals(tele)){
					
					return  "notelephone";
				}
				if(!email2.equals(email)){
					
					return  "noemail";
				}
			}else{
				return "noexit";
			}
			return "ok";//验证资料填写正确
		}
		
		public String update(String name,String password){
			Query q = entityManager.createQuery("select u from User u where u.username=:uname ");
			q.setParameter("uname", name);
			List results = q.getResultList();
			User user = (User)results.get(0);
			int id = user.getNumber();
			User uu = entityManager.find(User.class, id);
			uu.setPassword(password);
			entityManager.merge(uu);
			return "update";
		}
		
		//查询姓名
		public String queryName(int number){
			User u = entityManager.find(User.class, number);
			return u.getUsername();
		}
		
		//查询准考证号
		public int queryNumber(String name){
			Query q = entityManager.createQuery("select u from User u where u.username=:uname ");
			q.setParameter("uname", name);
			List results = q.getResultList();
			User user = (User)results.get(0);
			int id = user.getNumber();
			return id;
			
		}
}
