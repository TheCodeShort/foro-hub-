package com.foro.hub.infra.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration//se escanea primero ya que es por orden
public class SeguridadConfiguration {
	//autenticacion stayles
	public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity)throws Exception{
		return HttpSecurity.csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
	}
}
