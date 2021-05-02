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
    private String username;
    private String password;

    @OneToMany(mappedBy = "author")
    private List<OrderRequest> orderRequests;
    @OneToMany(mappedBy = "user")
    private List<BasketPosition> basketPositions;
}
