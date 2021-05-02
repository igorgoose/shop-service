package by.bsu.tp.lab2.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static by.bsu.tp.lab2.model.UserAuthority.*;


public enum UserRole {
    ADMIN(EMPLOYEE, PRODUCTS_EDIT, REQUESTS_VIEW, ORDERS_VIEW, BASKET_VIEW, BASKET_EDIT),
    SALESMAN(EMPLOYEE, PRODUCTS_VIEW, ORDERS_EDIT, BASKET_VIEW, BASKET_EDIT),
    CLERK(EMPLOYEE, PRODUCTS_VIEW, REQUESTS_EDIT, BASKET_VIEW, BASKET_EDIT),
    CLIENT(USER, PRODUCTS_VIEW, BASKET_VIEW, BASKET_EDIT);

    UserRole(UserAuthority... userAuthorities) {
        authorities = Arrays.stream(userAuthorities).map(UserAuthority::getAuthority).collect(Collectors.toList());
    }

    @Getter
    private final List<SimpleGrantedAuthority> authorities;


}
