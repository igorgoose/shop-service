package by.bsu.tp.lab2.config;

import by.bsu.tp.lab2.repository.UserRepository;
import by.bsu.tp.lab2.service.UserService;
import by.bsu.tp.lab2.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/products").hasAnyAuthority("products.view", "products.edit")
                .antMatchers("/products/**").hasAuthority("products.edit")
                .antMatchers("/requests", "/requests/{id:\\d+}").hasAnyAuthority("requests.view", "requests.edit")
                .antMatchers("/requests/**").hasAuthority("requests.edit")
                .antMatchers("/orders", "/orders/{id:\\d+}").hasAnyAuthority("orders.view", "orders.edit")
                .antMatchers("/orders/**").hasAuthority("orders.edit")
                .antMatchers("/", "/home").hasAnyAuthority("employee", "user")
                .antMatchers("/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, passwordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return userService();
    }
}
