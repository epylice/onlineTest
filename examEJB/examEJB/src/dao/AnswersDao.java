package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Answers;

@Stateless
public class AnswersDao implements AnswersDaoRemote{
	
	@PersistenceContext(unitName = "examEJB")
	private EntityManager entityManager;
	
	public AnswersDao(){
		
	}

	public String  queryAnswer(int number,int paper_id,int question_id){
		Query q = entityManager.createQuery("select a from Answers  a where a.number=?1 and a.paper_id=?2 and a.question_id=?3");
	    q.setParameter(1, number);
	    q.setParameter(2, paper_id);
	    q.setParameter(3, question_id);
	    List list = q.getResultList();
	    Answers a = (Answers)list.get(0);
	    return a.getAnswer();
	}
	
	public void add(int paperId,int questionId,int number,String answer){
		Answers a = new Answers();
		a.setAnswer(answer);
		a.setNumber(number);
		a.setPaper_id(paperId);
		a.setQuestion_id(questionId);
		entityManager.persist(a);
	}
}
