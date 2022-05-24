package com.kranius.fetcher.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.kranius.fetcher.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin1 = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("azerty1234"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(admin1);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/index", "/css/*", "/js/*").permitAll()


//                .antMatchers(HttpMethod.GET, "/*").permitAll()
                .antMatchers(HttpMethod.GET, "/app/**").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers(HttpMethod.GET, "/admin/**").hasRole(ADMIN.name())


//                .antMatchers(HttpMethod.DELETE, "/supprimmerRecommandation/{id}").hasAnyRole("GUIDE", "ORGA")

//                .antMatchers("/participant/**", "/role**", "/admin/**", "/evaluation/**").hasRole("ORGA")
//                .anyRequest().denyAll()

                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .httpBasic();

    }
}