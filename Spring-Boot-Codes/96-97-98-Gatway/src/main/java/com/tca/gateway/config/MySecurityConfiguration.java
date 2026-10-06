package com.tca.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class MySecurityConfiguration {
	
	@Bean(name = "springSecurityFilterChain")
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
		.oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
		
		httpSecurity.csrf(csrf -> csrf.disable());
		return httpSecurity.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
	    return JwtDecoders.fromIssuerLocation(properties.getJwt().getIssuerUri());
	}


}

