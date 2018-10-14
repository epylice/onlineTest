package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface QuestionDaoRemote {
	public List showAll();
	public void updateQuestion(int questionId,String context,String option,String answer);
	public Map<String,Object> show(int id);
	public void addQuestion(String context,String option,String answer);
}
