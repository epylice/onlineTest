package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;



@Remote
public interface PaperQuestionDaoRemote {
	public void addPaper(ArrayList<Map<String,Object>> list);
	public List show(int paperId);
	
}
