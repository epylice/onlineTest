package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollectQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cq_id;
	private int number;
	private int question_id;
	
	public CollectQuestion(){
		
	}
	public int getCq_id() {
		return cq_id;
	}
	public void setCq_id(int cq_id) {
		this.cq_id = cq_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	

	
}
