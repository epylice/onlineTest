package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.PaperQuestion;

@Stateless
public class PaperQuestionDao implements PaperQuestionDaoRemote{

	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	//ɾ���Ծ��е�������Ŀ
	public void deletePaper(int paperId){
		Query q = entityManager.createQuery("select u from PaperQuestion u where u.paper_id=:id ");
		q.setParameter("id", paperId);
		List list = q.getResultList();
		Iterator it = list.iterator();
		while(it.hasNext()){
			PaperQuestion pq = (PaperQuestion)it.next();
			entityManager.remove(pq);
		}
	}
	
	//ɾ���Ծ��е�ĳ����
	public void deleteQuestion(int paperId,int questionId){
		Query q = entityManager.createQuery("select u from PaperQuestion u where u.paper_id=:id and u.question_id=:questionId");
		q.setParameter("id", paperId);
		q.setParameter("questionId", questionId);
		List list = q.getResultList();
		PaperQuestion pq = (PaperQuestion)list.get(0);
		entityManager.remove(pq);
	}
	
	//����һ���Ծ�
	public void addPaper(ArrayList<Map<String,Object>> list){
		for(Map map :list){
			
			PaperQuestion paperQuestion = new PaperQuestion();
			int id = (Integer) map.get("paperId");
			System.out.println(id+"}}}");
			paperQuestion.setPaper_id(id);
			paperQuestion.setQuestion_id((Integer)map.get("questionId"));
			entityManager.persist(paperQuestion);
		}
	}
	
	////��ʾĳ���Ծ��е�������Ŀid
	public List show(int paperId){
		Query q = entityManager.createQuery("select u from PaperQuestion u where u.paper_id=:id");
		q.setParameter("id", paperId);
		List<Integer> l = new ArrayList<Integer>();
		List list = q.getResultList();
		Iterator it = list.iterator();
		while(it.hasNext()){
			PaperQuestion pq = (PaperQuestion)it.next();
			l.add(pq.getQuestion_id());
		}
		return l;
		//PaperQuestion pq = (PaperQuestion)list.get(0);
		
	}
}
