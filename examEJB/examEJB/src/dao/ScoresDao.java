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

import bean.Scores;

@Stateless
public class ScoresDao implements ScoresDaoRemote{

	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	public List<Map<String,Object>> showScores(int paperId){
		Query q = entityManager.createQuery("select s from Scores s where s.paper_id=?1 order by score desc");
		q.setParameter(1, paperId);
		List list = q.getResultList();
		Iterator it = list.iterator();
		ArrayList<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			Scores s = (Scores)it.next();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("score_id", s.getScore_id());
			map.put("paper_id", s.getPaper_id());
			map.put("number", s.getNumber());
			map.put("score", s.getScore());
			l.add(map);
			
		}
		return l;
	}
	
	//²éÑ¯·ÖÊý
	public int queryScore(int number,int paperId){
		Query q = entityManager.createQuery("select s from Scores s where s.paper_id=?1 and s.number=?2");
		q.setParameter(1, paperId);
		q.setParameter(2, number);
		List list = q.getResultList();
		return ((Scores)list.get(0)).getScore();
	}
	
	public void add(int paperId,int number,int score){
		Scores s = new Scores();
		s.setNumber(number);
		s.setPaper_id(paperId);
		s.setScore(score);
		entityManager.persist(s);
	}
}
