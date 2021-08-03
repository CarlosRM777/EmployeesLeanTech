package lean.tech.dto;

import java.util.List;
import java.util.ArrayList;

import lean.tech.model.Employee;
import lean.tech.model.Person;

public class EmployeeByPositionDTO {
	Long id;
	String name;
	List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
	
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
	public List<EmployeeDto> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
	
	public class EmployeeDto {
		Long id;
		Double salary;
		PersonDto person;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Double getSalary() {
			return salary;
		}
		public void setSalary(Double salary) {
			this.salary = salary;
		}
		public PersonDto getPerson() {
			return person;
		}
		public void setPerson(PersonDto person) {
			this.person = person;
		}
		public EmployeeDto(Long id, Double salary, Person person2) {
			super();
			this.id = id;
			this.salary = salary;
			this.person = new PersonDto(person2);
		}
		
		public class PersonDto {
			public PersonDto(Person person2) {
				this.name = person2.getName();
				this.lastnam2 = person2.getLastname();
				this.address = person2.getAddress();
				this.cellphone = person2.getCellphone();
				this.cityName = person2.getCityName();
			}
			String name;
			String lastnam2;
			String address;
			String cellphone;
			String cityName;
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getLastnam2() {
				return lastnam2;
			}
			public void setLastnam2(String lastnam2) {
				this.lastnam2 = lastnam2;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getCellphone() {
				return cellphone;
			}
			public void setCellphone(String cellphone) {
				this.cellphone = cellphone;
			}
			public String getCityName() {
				return cityName;
			}
			public void setCityName(String cityName) {
				this.cityName = cityName;
			}
		}
	}

	public void addnewEmployee(Employee pemployee) {
		this.getEmployees().add(new EmployeeDto(pemployee.getId(), pemployee.getSalary(), pemployee.getPerson()));
	}
	
}
