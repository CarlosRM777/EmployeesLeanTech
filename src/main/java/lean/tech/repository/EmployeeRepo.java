package lean.tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lean.tech.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	List<Employee> findAllByPositionNameOrPersonName(String pPositionName, String pPersonName);
	List<Employee> findAllByPositionIdOrderBySalaryDesc(Long pId);
}
