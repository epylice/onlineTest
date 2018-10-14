package bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manager {

	@Id
	private String sfz;
	private String name;
	private String password;
	
	public Manager(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
