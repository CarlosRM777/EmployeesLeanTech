package lean.tech.model;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Position {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	
	//@OneToMany(mappedBy = "position")
//	@OneToMany(targetEntity = Employee.class)
//    @JoinColumn(referencedColumnName = "id")
//	List<Employee> LEmployee = new ArrayList<Employee>();
	
	public Position(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Position() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*
	 * public List<Employee> getLEmployee() { return LEmployee; } public void
	 * setLEmployee(List<Employee> lEmployee) { LEmployee = lEmployee; }
	 */
	
}
