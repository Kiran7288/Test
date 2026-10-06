package com.tca.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyApplicationSecurity 
{
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers(HttpMethod.GET, "/{id}", "/books").authenticated() // authenticated and authorized user only can access
									//	.requestMatchers(HttpMethod.GET, "/{id}", "/books").permitAll()  // every one can access
									//.requestMatchers(HttpMethod.DELETE, "/{id}").authenticated()   //[L64 26.41]
				                      .requestMatchers(HttpMethod.DELETE, "/{id}").hasAnyRole("ADMIN")
				                      .requestMatchers("/create").hasAnyRole("MANAGER")
				)
		        .formLogin(Customizer.withDefaults());
		
		http.csrf(csrf -> csrf.disable());
	
		return http.build();
	}
	
	@Bean
	public UserDetailsService  userDetailsService() {
		
		UserDetails  admin = User.builder().username("John").password(passwordEncoder().encode("john@123")).roles("ADMIN").build();
		UserDetails  manager = User.builder().username("Alice").password(passwordEncoder().encode("alice@007")).roles("MANAGER").build();
		return new InMemoryUserDetailsManager(admin, manager);
	}
	
	@Bean
	public PasswordEncoder  passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
}
