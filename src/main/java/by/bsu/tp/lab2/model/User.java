package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String role;

    @OneToMany(mappedBy = "author")
    private List<OrderRequest> orderRequests;
}
