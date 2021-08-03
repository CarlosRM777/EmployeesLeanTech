package lean.tech.employees.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lean.tech.model.Employee;
import lean.tech.model.Person;
import lean.tech.model.Position;
import lean.tech.repository.EmployeeRepo;
import lean.tech.service.EmployeeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@Transactional
public class EmployeeServiceTest {
	@Autowired
	EmployeeService employeeservice;
	
	@Autowired
	EmployeeRepo employeerepo;
	
	@Order(1)
	@Test
    public void shouldCreateEmployee() throws Exception {
        Employee result = employeeservice.CreateEmployee(createTestEmployee1());
        assertThat(result).isNotNull();
        //assertThat(result).extracting(Employee::getId).isEqualTo(result.getId());
        assertThat(result).extracting(Employee::getSalary).isEqualTo(4000.00);
        assertThat(result.getPerson()).extracting(Person::getName).isEqualTo("Carlos");
        assertThat(result.getPerson()).extracting(Person::getCityName).isEqualTo("Arequipa");
        assertThat(result.getPosition()).extracting(Position::getName).isEqualTo("DEV");
    }
	
	/*
	 * @Order(2)
	 * 
	 * @Test public void shouldDeleteEmployee() throws Exception { Employee inserted
	 * = employeeservice.CreateEmployee(createTestEmployee2());
	 * 
	 * assertThat(inserted).isNotNull();
	 * assertThat(inserted.getPerson()).extracting(Person::getName).isEqualTo(
	 * "Roberto");
	 * 
	 * employeerepo.delete(inserted);
	 * 
	 * //Boolean result = employeeservice.DeleteEmployee(inserted.getId());
	 * //assertThat(result).isNotNull(); //assertThat(result).isTrue();
	 * 
	 * List<Employee> result2 = employeeservice.FindAll();
	 * assertThat(result2).isNotNull(); assertThat(result2).hasSize(0); }
	 */
    
	@Order(3)
    @Test
    public void shouldUpdateEmployee() throws Exception {
		Employee ToUpdate = employeeservice.CreateEmployee(createTestEmployee2());
		ToUpdate.setSalary(2000.00);
		ToUpdate.setPerson(new Person(5L, "Julio", "Cisneros", "Vea", "123456", "Medellin"));
		Employee EmployeeUpdated = employeeservice.UpdateEmployee(ToUpdate);
		assertThat(EmployeeUpdated).isNotNull();
		assertThat(EmployeeUpdated).extracting(Employee::getId).isEqualTo(EmployeeUpdated.getId());
        assertThat(EmployeeUpdated).extracting(Employee::getSalary).isEqualTo(2000.00);
        assertThat(EmployeeUpdated.getPerson()).extracting(Person::getName).isEqualTo("Julio");
        assertThat(EmployeeUpdated.getPerson()).extracting(Person::getCityName).isEqualTo("Medellin");
        assertThat(EmployeeUpdated.getPerson()).extracting(Person::getCellphone).isEqualTo("123456");
    }
    
	@Order(4)
    @Test
    public void shouldFindbyEmployeeName() throws Exception {
		Employee inserted = employeeservice.CreateEmployee(createTestEmployee3());
		//Employee inserted = employeeservice.CreateEmployee(createTestEmployee3());
		List<Employee> result = employeeservice.FindAllbyPositionNameorEmployeeName("", "Roberto");
		assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).first().extracting(Employee::getId).isEqualTo(inserted.getId());
        assertThat(result).first().extracting(Employee::getSalary).isEqualTo(4500.00);
    }
    
    
    private Employee createTestEmployee1() {
    	Employee employee = new Employee();
    	employee.setPerson(new Person(1L, "Carlos", "Rodriguez", "Pershing", "94321", "Arequipa"));
    	employee.setPosition(new Position(1L, "DEV"));
    	employee.setSalary(4000.00);
        return employee;
    }
    private Employee createTestEmployee2() {
    	Employee employee = new Employee();
    	employee.setPerson(new Person(2L, "Roberto", "Grande", "Vea", "123456", "Antoquia"));
    	employee.setPosition(new Position(1L, "DEV"));
    	employee.setSalary(4500.00);
        return employee;
    }
    private Employee createTestEmployee3() {
    	Employee employee = new Employee();
    	employee.setPerson(new Person(3L, "Roberto", "Linca", "Caraballa", "432562", "Antoquia"));
    	employee.setPosition(new Position(1L, "DEV"));
    	employee.setSalary(4500.00);
        return employee;
    }
}
