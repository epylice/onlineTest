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

@Stateless
public class CollectPaperDao implements CollectPaperDaoRemote{

	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	public CollectPaperDao(){
		
	}
	
	public void add(int number,int paper_id){
		
		CollectPaper cp = new CollectPaper();
		cp.setNumber(number);
		cp.setPaper_id(paper_id);
		entityManager.persist(cp);
	}
	
	public void delete(int cp_id){
		CollectPaper cp = entityManager.find(CollectPaper.class, cp_id);
		entityManager.remove(cp);
	}
	
	public List<Map<String,Object>> show(int number){
		Query q = entityManager.createQuery("select  cp from CollectPaper cp where cp.number=?1");
		q.setParameter(1, number);
		List list = q.getResultList();
		Iterator it = list.iterator();
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			CollectPaper cp = (CollectPaper)it.next();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cpId", cp.getCp_id());
			map.put("number",cp.getNumber());
			map.put("paperId",cp.getPaper_id());
			l.add(map);
		}
		return l;
	}
}
