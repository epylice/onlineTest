package dao;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EnterDaoRemote {

	public void enter(int number,int paperId);
	public boolean  isEnter(int number,int paperId);
	public List<Integer> queryExam(int number);
	public List<Integer> queryHeld(int number);
	public void update(int number,int paperId);
}
