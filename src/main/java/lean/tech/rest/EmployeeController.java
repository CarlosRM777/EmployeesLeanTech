package lean.tech.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lean.tech.dto.EmployeeByPositionDTO;
import lean.tech.model.Employee;
import lean.tech.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeservice;
	
	@PostMapping("/create")
	//public ResponseEntity<Employee> CreateEmployee(@RequestBody EmployeeDto pnewEmployee) {
	public ResponseEntity<Employee> CreateEmployee(@RequestBody Employee pnewEmployee) {
		Employee EmpResult = employeeservice.CreateEmployee(pnewEmployee);
		if (EmpResult.equals(null))
			return new ResponseEntity<Employee>(EmpResult, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Employee>(EmpResult, HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Employee> UpdateEmployee(@RequestBody Employee pupdEmployee) {
		Employee EmpResult =  employeeservice.UpdateEmployee(pupdEmployee);
		if (EmpResult != null)
			return new ResponseEntity<Employee>(EmpResult, HttpStatus.OK);
		else
			return new ResponseEntity<Employee>(EmpResult, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{pId}")
	public ResponseEntity<String> DeleteEmployee(@PathVariable Long pId) {
		if (employeeservice.DeleteEmployee(pId))
			return new ResponseEntity<String>("Se Eliminó el Elemento CORRECTAMENTE !!", HttpStatus.OK);
		else 
			return new ResponseEntity<String>("No se encontró el Elemento =(", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/findbynames")
	public List<Employee> FindAllbyPositionNameorEmployeeName(@RequestParam(name = "cargo", required = false, defaultValue = "") String pPositionName, @RequestParam(name = "nombre", required = false, defaultValue = "") String pEmployeeName) {
		List<Employee> LResult = new ArrayList<Employee>(); 
		if (pPositionName.length()!=0 || pEmployeeName.length()!=0)
			LResult = employeeservice.FindAllbyPositionNameorEmployeeName(pPositionName,pEmployeeName);
		else 
			LResult = employeeservice.FindAll();
		return LResult;
	}
	
	@GetMapping("/findall")
	public List<EmployeeByPositionDTO> FindAllPositionsandEmployees() {
		return employeeservice.FindAllPositionsandEmployees();
	}
	
	public EmployeeController() {
		// TODO Auto-generated constructor stub
	}

}
