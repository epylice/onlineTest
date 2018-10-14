package dao;

import javax.ejb.Remote;

@Remote
public interface AnswersDaoRemote {
	public String  queryAnswer(int number,int paper_id,int question_id);
	public void add(int paperId,int questionId,int number,String answer);
}
