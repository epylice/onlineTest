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
	//����
	public void enter(int number,int paperId){
		Enter e = new Enter();
		e.setNumber(number);
		e.setPaper_id(paperId);
		e.setState("�ѱ���");
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
	
	//��ѯһ���ѱ����Ŀ��ԣ�δ�μӿ��ԣ�
	public List<Integer> queryExam(int number){
		Query q = entityManager.createQuery("select e from Enter e where e.number=?1 and e.state=?2 ");
		q.setParameter(1, number);
		q.setParameter(2, "�ѱ���");
		List list = q.getResultList();
		Iterator it = list.iterator();
		ArrayList<Integer> l = new ArrayList<Integer>();
		while(it.hasNext()){
			Enter e = (Enter)it.next();
			l.add(e.getPaper_id());
			
		}
		return l;
	}
	
	//��ѯһ������ɵĿ��ԣ��Ѳμӿ��ԣ�
		public List<Integer> queryHeld(int number){
			Query q = entityManager.createQuery("select e from Enter e where e.number=?1 and e.state=?2 ");
			q.setParameter(1, number);
			q.setParameter(2, "�����");
			List list = q.getResultList();
			Iterator it = list.iterator();
			ArrayList<Integer> l = new ArrayList<Integer>();
			while(it.hasNext()){
				Enter e = (Enter)it.next();
				l.add(e.getPaper_id());
				
			}
			return l;
		}
		
		//�޸�״̬
		public void update(int number,int paperId){
			Query q = entityManager.createQuery("update Enter e set e.state='�����' where e.number=?1 and e.paper_id=?2 ");
			q.setParameter(1, number);
			q.setParameter(2, paperId);
			int result = q. executeUpdate (); 
		}
}
