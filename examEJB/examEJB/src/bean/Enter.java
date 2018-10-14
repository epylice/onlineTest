package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int enter_id;
	private int paper_id;
	private int number;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Enter(){
		
	}
	public int getEnter_id() {
		return enter_id;
	}
	public void setEnter_id(int enter_id) {
		this.enter_id = enter_id;
	}
	public int getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
