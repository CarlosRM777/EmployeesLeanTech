package lean.tech.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lean.tech.dto.EmployeeByPositionDTO;
import lean.tech.model.Employee;
import lean.tech.model.Position;
import lean.tech.repository.EmployeeRepo;
import lean.tech.repository.PersonRepo;
import lean.tech.repository.PositionRepo;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	EmployeeRepo employeerepo;
	@Autowired
	PersonRepo personrepo;
	@Autowired
	PositionRepo positionrepo;
	
	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee CreateEmployee(Employee pemployee) {
		if (pemployee != null)
		{
			personrepo.save(pemployee.getPerson());
			positionrepo.save(pemployee.getPosition());
			return employeerepo.save(pemployee);
		}
		return null;
	}
	
	public Boolean DeleteEmployee(Long pid) {
		Optional<Employee> Optemployee = employeerepo.findById(pid);
		if (Optemployee.isPresent()) {
			employeerepo.deleteById(pid);
			return true;
		}
		return false;
	}
	
	public Employee UpdateEmployee(Employee pemployee) {
		Optional<Employee> Optemployee = employeerepo.findById(pemployee.getId());
		Employee oldEmployee = Optemployee.get();
		if (Optemployee.isPresent()) {
			if (pemployee.getPerson() != null && pemployee.getPerson().getId() != null) 
				oldEmployee.setPerson(personrepo.save(pemployee.getPerson()));
			//else 
			//	pemployee.setPerson(oldEmployee.getPerson());
			
			if (pemployee.getPosition() != null && pemployee.getPosition().getId() != null)
				oldEmployee.setPosition(positionrepo.save(pemployee.getPosition()));
			//else
			//	pemployee.setPosition(oldEmployee.getPosition());
			oldEmployee.setSalary(pemployee.getSalary());
			return oldEmployee;
			//return employeerepo.save(pemployee);
		}
		return null;
	}

	public List<Employee> FindAllbyPositionNameorEmployeeName(String pPositionName, String pPersonName) {
		// TODO Auto-generated method stub
		return employeerepo.findAllByPositionNameOrPersonName(pPositionName, pPersonName);
	}
	
	public List<Employee> FindAll() {
		// TODO Auto-generated method stub
		return employeerepo.findAll();
	}

	public List<EmployeeByPositionDTO> FindAllPositionsandEmployees() {
		// TODO Auto-generated method stub
		List<EmployeeByPositionDTO> Result = new ArrayList<EmployeeByPositionDTO>();
		for (Position pos : positionrepo.findAll()) {
			EmployeeByPositionDTO tmpEmployeeDto = new EmployeeByPositionDTO();
			tmpEmployeeDto.setId(pos.getId());
			tmpEmployeeDto.setName(pos.getName());
			for (Employee emp : employeerepo.findAllByPositionIdOrderBySalaryDesc(pos.getId())) 
				tmpEmployeeDto.addnewEmployee(emp);
			Result.add(tmpEmployeeDto);
		}
		return Result;
	}

}
