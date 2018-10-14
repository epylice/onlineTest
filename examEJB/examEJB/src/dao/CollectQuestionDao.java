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

import bean.CollectPaper;
import bean.CollectQuestion;

@Stateless
public class CollectQuestionDao implements CollectQuestionDaoRemote{

	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	public CollectQuestionDao(){
		
	}
	
	public void add(int number,int question_id){
		CollectQuestion c = new CollectQuestion();
		c.setNumber(number);
		c.setQuestion_id(question_id);
		entityManager.persist(c);
	}
	
	public void delete(int cq_id){
		CollectQuestion c = entityManager.find(CollectQuestion.class, cq_id);
		entityManager.remove(c);
	}
	
	public List<Map<String,Object>> show(int number){
		Query q = entityManager.createQuery("select  cp from CollectQuestion cp where cp.number=?1");
		q.setParameter(1, number);
		List list = q.getResultList();
		Iterator it = list.iterator();
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			CollectQuestion cp = (CollectQuestion)it.next();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cpId", cp.getCq_id());
			map.put("number",cp.getNumber());
			map.put("questionId",cp.getQuestion_id());
			l.add(map);
		}
		return l;
	}
}
