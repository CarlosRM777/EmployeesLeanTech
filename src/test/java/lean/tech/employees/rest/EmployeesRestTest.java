package lean.tech.employees.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lean.tech.rest.EmployeeController;
import lean.tech.service.EmployeeService;
import lean.tech.model.Employee;
import lean.tech.model.Person;
import lean.tech.model.Position;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeesRestTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmployeeService employeeservice;

	@Test
	public void shouldReturnAllEmployees() throws Exception {

		// given
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(createTestEmployee1());
		employees.add(createTestEmployee2());
		employees.add(createTestEmployee3());
		// when
		when(employeeservice.FindAllbyPositionNameorEmployeeName("DEV", "")).thenReturn(employees);

		// then
		mockMvc.perform(get("/employee/findbynames?cargo=DEV").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].person.name", is("Carlos")))
				.andExpect(jsonPath("$[1].person.name", is("Roberto")))
				.andExpect(jsonPath("$[2].person.name", is("Julio")));
	}

	@Test
	public void shouldDeleteEmployee() throws Exception {

		// given
		Boolean deletedOk = true;

		// when
		when(employeeservice.DeleteEmployee(1L)).thenReturn(deletedOk);

		// then
		mockMvc.perform(delete("/employee/delete/1"))
				.andExpect(status().isOk());
	}
	
	/*
	 * @Test public void shouldUpdateEmployee() throws Exception {
	 * 
	 * // given
	 * 
	 * Employee toUpdate = createTestEmployee3(); toUpdate.setId(3L); Employee
	 * response = createTestEmployee3(); response.setId(3L); // when
	 * when(employeeservice.UpdateEmployee(toUpdate)).thenReturn(response);
	 * 
	 * // then mockMvc.perform(post("/employee/update")
	 * .content(objectMapper.writeValueAsBytes(toUpdate))
	 * .contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
	 * .andExpect(jsonPath("$.salary", is(4500.00))) .andExpect(status().isOk()); }
	 */

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
    	employee.setSalary(3200.00);
        return employee;
    }
    private Employee createTestEmployee3() {
    	Employee employee = new Employee();
    	employee.setPerson(new Person(3L, "Julio", "Linca", "Caraballa", "432562", "Antoquia"));
    	employee.setPosition(new Position(1L, "DEV"));
    	employee.setSalary(4400.00);
        return employee;
    }
}