package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Enter;
import bean.Paper;

@Stateless
public class EnterDao implements EnterDaoRemote{

	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	public EnterDao(){
		
	}
	//报名
	public void enter(int number,int paperId){
		Enter e = new Enter();
		e.setNumber(number);
		e.setPaper_id(paperId);
		e.setState("已报名");
		entityManager.persist(e);
	}
	
	public boolean  isEnter(int number,int paperId){
		Query q = entityManager.createQuery("select e from Enter e where e.number=?1 and e.paper_id=?2");
		q.setParameter(1, number);
		q.setParameter(2, paperId);
		List list = q.getResultList();
		if(list.size()>0){
			return false;
		}
		return true;
	}
	
	//查询一个已报名的考试（未参加考试）
	public List<Integer> queryExam(int number){
		Query q = entityManager.createQuery("select e from Enter e where e.number=?1 and e.state=?2 ");
		q.setParameter(1, number);
		q.setParameter(2, "已报名");
		List list = q.getResultList();
		Iterator it = list.iterator();
		ArrayList<Integer> l = new ArrayList<Integer>();
		while(it.hasNext()){
			Enter e = (Enter)it.next();
			l.add(e.getPaper_id());
			
		}
		return l;
	}
	
	//查询一个已完成的考试（已参加考试）
		public List<Integer> queryHeld(int number){
			Query q = entityManager.createQuery("select e from Enter e where e.number=?1 and e.state=?2 ");
			q.setParameter(1, number);
			q.setParameter(2, "已完成");
			List list = q.getResultList();
			Iterator it = list.iterator();
			ArrayList<Integer> l = new ArrayList<Integer>();
			while(it.hasNext()){
				Enter e = (Enter)it.next();
				l.add(e.getPaper_id());
				
			}
			return l;
		}
		
		//修改状态
		public void update(int number,int paperId){
			Query q = entityManager.createQuery("update Enter e set e.state='已完成' where e.number=?1 and e.paper_id=?2 ");
			q.setParameter(1, number);
			q.setParameter(2, paperId);
			int result = q. executeUpdate (); 
		}
}
