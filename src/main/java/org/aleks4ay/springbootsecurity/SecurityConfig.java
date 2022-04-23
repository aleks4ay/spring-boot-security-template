package org.aleks4ay.springbootsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
            .and()
                .formLogin()
            .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$1hBPbesIeA3/tM7wNn9fR.mxReWgH2p6STEHYuSuGBbzPIQRpi0gK") //100
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$E7ypqHeJWBfABv/WsJRFWecgzQKeYkUyFaS/x4G9Mq6ooq8OK7Wtm") //admin
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        if (!users.userExists(user.getUsername())) {
            users.createUser(user);
        }
        if (!users.userExists(admin.getUsername())) {
            users.createUser(admin);
        }
        return users;
    }
}
