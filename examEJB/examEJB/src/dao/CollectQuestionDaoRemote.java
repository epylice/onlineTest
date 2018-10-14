package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface CollectQuestionDaoRemote {

	public void add(int number,int question_id);
	public void delete(int cq_id);
	public List<Map<String,Object>> show(int number);
}
