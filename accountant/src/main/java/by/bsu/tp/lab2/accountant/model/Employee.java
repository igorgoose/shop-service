package by.bsu.tp.lab2.accountant.model;

import lombok.Data;

import java.util.List;

@Data
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
    private String password;

    private List<OrderRequest> orderRequests;
}
