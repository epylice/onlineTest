package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollectPaper {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cp_id;
	private int number;
	private int paper_id;
	
public CollectPaper(){
		
	}
	public int getCp_id() {
		return cp_id;
	}
	public void setCp_id(int cp_id) {
		this.cp_id = cp_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}

}
