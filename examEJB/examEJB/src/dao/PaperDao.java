package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Paper;
import bean.PaperQuestion;
import bean.Question;

@Stateless
public class PaperDao implements PaperDaoRemote{
	
	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	
	//添加试卷
	public ArrayList<Map<String,Object>> addPaper(String type,String time,ArrayList<Integer> qid){
		System.out.println(type+"*******8"+time+"&&&"+qid.get(0));
		Paper paper = new Paper();
		paper.setType(type);
		paper.setTime(time);
		entityManager.persist(paper);
		Query q = entityManager.createQuery("select p from Paper p where p.type=:type and p.time=:time");
		q.setParameter("type", type);
		q.setParameter("time", time);
		List list = q.getResultList();
		System.out.println(list.size()+"@@@@@@");
		ArrayList l = new ArrayList<Map<String,Object>>();
		int paperId = ((Paper)list.get(0)).getPaper_id();
		System.out.println(paperId+"????????????//");
		for(int i = 0;i<qid.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("paperId",paperId );
			map.put("questionId",qid.get(i));
			
			l.add(map);
		}
		System.out.println(l.size()+"{{{{{");
		return l;
	}

	//删除一套试卷
	public void deletePaper(int paperId){
		PaperQuestionDao pqd = new PaperQuestionDao();
		pqd.deletePaper(paperId);
		Paper paper = entityManager.find(Paper.class, paperId);
		entityManager.remove(paper);
		
	}
	
	
	
	//查看最近考试信息
	public List<Map<String,Object>> lookExamInformation(){
		Date d = new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		String time = f.format(d);
		
		Query q = entityManager.createQuery("select p from Paper p where p.time>=?1");
		q.setParameter(1, time);
		List list = q.getResultList();
		
		Iterator it = list.iterator();
		ArrayList<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			Paper p = (Paper)it.next();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("paperId", p.getPaper_id());
			map.put("type", p.getType());
			map.put("time", p.getTime());
			l.add(map);
		}
		return l;
	}
	
	//查看之前已举办的考试信息
	public List<Map<String,Object>> lookheld(){
		Date d = new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		String time = f.format(d);
		
		Query q = entityManager.createQuery("select p from Paper p where p.time<?1");
		q.setParameter(1, time);
		List list = q.getResultList();
		
		Iterator it = list.iterator();
		ArrayList<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			Paper p = (Paper)it.next();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("paperId", p.getPaper_id());
			map.put("type", p.getType());
			map.put("time", p.getTime());
			l.add(map);
		}
		return l;
	}
	
	
	//根据id查询考试信息
	public Map<String,Object> queryPaper(int paperId){
		Query q = entityManager.createQuery("select p from Paper p where p.paper_id=?1");
		q.setParameter(1, paperId);
		List list = q.getResultList();
		Paper p = (Paper)list.get(0);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paperId", p.getPaper_id());
		map.put("type", p.getType());
		map.put("time", p.getTime());
		return map;
	}
}
