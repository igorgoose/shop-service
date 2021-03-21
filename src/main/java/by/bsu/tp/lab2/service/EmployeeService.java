package by.bsu.tp.lab2.service;

import by.bsu.tp.lab2.model.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EmployeeService extends UserDetailsService {
    Employee getUserById(long id);
    Employee registerEmployee(Employee employee);
}
