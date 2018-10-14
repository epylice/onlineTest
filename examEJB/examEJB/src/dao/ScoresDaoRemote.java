package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface ScoresDaoRemote {
	public List<Map<String,Object>> showScores(int paperId);
	public int queryScore(int number,int paperId);
	public void add(int paperId,int number,int score);
}
