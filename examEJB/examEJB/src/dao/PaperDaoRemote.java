package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface PaperDaoRemote {

	public List<Map<String,Object>> lookExamInformation();
	public List<Map<String,Object>> lookheld();
	public ArrayList<Map<String,Object>> addPaper(String type,String time,ArrayList<Integer> qid);
	public Map<String,Object> queryPaper(int paperId);
}
