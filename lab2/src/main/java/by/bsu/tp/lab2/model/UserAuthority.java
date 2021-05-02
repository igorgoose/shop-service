package by.bsu.tp.lab2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
public enum UserAuthority {
    EMPLOYEE(new SimpleGrantedAuthority("employee")),
    USER(new SimpleGrantedAuthority("user")),
    PRODUCTS_VIEW(new SimpleGrantedAuthority("products.view")),
    PRODUCTS_EDIT(new SimpleGrantedAuthority("products.edit")),
    REQUESTS_VIEW(new SimpleGrantedAuthority("requests.view")),
    REQUESTS_EDIT(new SimpleGrantedAuthority("requests.edit")),
    ORDERS_VIEW(new SimpleGrantedAuthority("orders.view")),
    ORDERS_EDIT(new SimpleGrantedAuthority("orders.edit")),
    BASKET_VIEW(new SimpleGrantedAuthority("basket.view")),
    BASKET_EDIT(new SimpleGrantedAuthority("basket.edit"));


    @Getter
    private final SimpleGrantedAuthority authority;


}
