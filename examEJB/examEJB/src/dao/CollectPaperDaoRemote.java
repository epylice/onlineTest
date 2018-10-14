package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface CollectPaperDaoRemote {

	public void add(int number,int paper_id);
	public void delete(int cp_id);
	public List<Map<String,Object>> show(int number);
}
