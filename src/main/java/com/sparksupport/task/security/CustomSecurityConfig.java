package com.sparksupport.task.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService());
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(User.withUsername("admin").password("{noop}password").roles("ADMIN").build());
	        manager.createUser(User.withUsername("user").password("{noop}password").roles("USER").build());
	        return manager;
	    }
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.inMemoryAuthentication()
//	        .passwordEncoder(passwordEncoder())
//	        .withUser("admin")
//	        .password(passwordEncoder().encode("sparksupport"))
//	        .roles("ADMIN")
//	        .and().withUser("user")
//	        .password(passwordEncoder().encode("spark"))
//	        .roles("USER");
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .and()
	            .httpBasic();
	    }
}