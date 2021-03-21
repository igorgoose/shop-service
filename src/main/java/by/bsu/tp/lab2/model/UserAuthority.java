package by.bsu.tp.lab2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
public enum UserAuthority {
    EMPLOYEE(new SimpleGrantedAuthority("employee")),
    PRODUCTS_VIEW(new SimpleGrantedAuthority("products.view")),
    PRODUCTS_EDIT(new SimpleGrantedAuthority("products.edit")),
    REQUESTS_VIEW(new SimpleGrantedAuthority("requests.view")),
    REQUESTS_EDIT(new SimpleGrantedAuthority("requests.edit"));

    @Getter
    private final SimpleGrantedAuthority authority;


}
