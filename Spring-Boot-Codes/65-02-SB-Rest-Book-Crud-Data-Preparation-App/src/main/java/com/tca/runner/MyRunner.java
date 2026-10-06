package com.tca.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception 
	{
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		
		String insert_users = "INSERT INTO USERS(USERNAME, PASSWORD, ENABLED) VALUES(?, ?, ?)";
		String insert_authorities = "INSERT INTO AUTHORITIES(USERNAME, AUTHORITY) VALUES(?, ?)";
		
		jdbcTemplate.update(insert_users, "John", bCrypt.encode("john@123"), true);
		jdbcTemplate.update(insert_users, "Allen", bCrypt.encode("allen@123"), false);
		jdbcTemplate.update(insert_users, "Alice", bCrypt.encode("alice@123"), true);
		jdbcTemplate.update(insert_users, "David", bCrypt.encode("david@123"), false);
		jdbcTemplate.update(insert_users, "Mark", bCrypt.encode("mark@123"), true);
		
		jdbcTemplate.update(insert_authorities, "John", "ROLE_ADMIN");
		jdbcTemplate.update(insert_authorities, "Allen", "ROLE_MANAGER");
		jdbcTemplate.update(insert_authorities, "Alice", "ROLE_MANAGER");
		jdbcTemplate.update(insert_authorities, "David", "ROLE_ADMIN");
		jdbcTemplate.update(insert_authorities, "Mark", "ROLE_LEAD");
		
	}
	
}
