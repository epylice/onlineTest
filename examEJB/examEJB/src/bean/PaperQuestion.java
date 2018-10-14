package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaperQuestion { 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pq_id;
	
	private int paper_id;
	private int question_id;
	
	
	
	public PaperQuestion(){
		
	}
	public int getPaper_id() {
		return paper_id;
	}
	
	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}
	
	
	public int getQuestion_id() {
		return question_id;
	}
	
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	
	public int getPq_id() {
		return pq_id;
	}

	public void setPq_id(int pq_id) {
		this.pq_id = pq_id;
	}

}
