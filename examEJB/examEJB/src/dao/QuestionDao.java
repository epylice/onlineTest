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

import bean.PaperQuestion;
import bean.Question;

@Stateless
public class QuestionDao implements QuestionDaoRemote{

	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	//查询题库中所有题目
	public List<Map<String, Object>> showAll(){
		Query query = entityManager.createQuery("from Question c");
		List results = query.getResultList();
		Iterator it = results.iterator();
		ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			Question q = (Question)it.next();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("questionId", q.getQuestion_id());
			map.put("context", q.getContext());
			map.put("option", q.getOp());
			map.put("answer", q.getAnswer());
			list.add(map);
		}
		return list;
	}
	
	//增加一道题
	public void addQuestion(String context,String option,String answer){
		
		Question q = new Question();
		q.setContext(context);
		q.setAnswer(answer);
		q.setOp(option);
		
		entityManager.persist(q);
	}
	
	//修改题目
	public void updateQuestion(int questionId,String context,String op,String answer){
		//System.out.println(context+"option:"+option+"answer;"+answer+"}}}}");
		Question q = entityManager.find(Question.class, questionId);
		q.setContext(context);
		q.setAnswer(answer);
		q.setOp(op);
		
		entityManager.merge(q);
		
		/*Query q= entityManager.createQuery("update Question q set q.context=?1,q.option=?2,q.answer=?3 where q.question_id=?4");
		q.setParameter(1, context);
		q.setParameter(2, option);
		q.setParameter(3, answer);
		q.setParameter(4, questionId);
		q.executeUpdate();*/
		
	}
	//根据id查询题目
	public Map<String,Object> show(int id){
		
			Question q = entityManager.find(Question.class, id);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("questionId", q.getQuestion_id());
			map.put("context", q.getContext());
			map.put("option", q.getOp());
			map.put("answer", q.getAnswer());
			
		
		return map;
	}
}
