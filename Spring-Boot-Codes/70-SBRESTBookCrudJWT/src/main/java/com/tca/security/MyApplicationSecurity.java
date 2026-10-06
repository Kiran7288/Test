package com.tca.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tca.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class MyApplicationSecurity {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JwtFilter  jwtFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/login").permitAll()
				                                          .requestMatchers(HttpMethod.GET, "/{id}", "/books").hasAnyRole("ADMIN")
				                                          .requestMatchers(HttpMethod.DELETE, "/{id}").hasAnyRole("ADMIN")
				                                          .requestMatchers("/create").hasAnyRole("MANAGER")
				                                          
				                  )
								//.formLogin(Customizer.withDefaults());
		                          .formLogin(customizer -> customizer.disable());

		http.csrf(csrf -> csrf.disable());

		http.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails admin =
	 * User.builder().username("John").password(passwordEncoder().encode("john@123")
	 * ).roles("ADMIN").build(); UserDetails manager =
	 * User.builder().username("Alice").password(passwordEncoder().encode(
	 * "alice@007")).roles("MANAGER").build(); return new
	 * InMemoryUserDetailsManager(admin, manager); }
	 */

	@Bean
	public UserDetailsService userDetailsService() {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		return users;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

}
