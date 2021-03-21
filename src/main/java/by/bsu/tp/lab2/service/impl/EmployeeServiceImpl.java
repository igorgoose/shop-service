package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.Employee;
import by.bsu.tp.lab2.model.UserRole;
import by.bsu.tp.lab2.repsoitory.EmployeeRepository;
import by.bsu.tp.lab2.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUsers() {
        Employee admin = new Employee();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setRole(UserRole.ADMIN.name());
        registerEmployee(admin);
        Employee salesman = new Employee();
        salesman.setId(2L);
        salesman.setUsername("salesman");
        salesman.setPassword("salesman");
        salesman.setFirstName("Dohn");
        salesman.setLastName("Joe");
        salesman.setRole(UserRole.SALESMAN.name());
        registerEmployee(salesman);
        Employee clerk = new Employee();
        clerk.setId(3L);
        clerk.setUsername("clerk");
        clerk.setPassword("clerk");
        clerk.setFirstName("Jodn");
        clerk.setLastName("Hoe");
        clerk.setRole(UserRole.CLERK.name());
        registerEmployee(clerk);
    }

    @Override
    public Employee getUserById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found(id=" + id + ")"));
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return convertToUserDetails(employeeRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User " + s + "not found"))
        );
    }

    private User convertToUserDetails(Employee employee) {
        return new User(employee.getUsername(), employee.getPassword(), UserRole.valueOf(employee.getRole()).getAuthorities());
    }
}
