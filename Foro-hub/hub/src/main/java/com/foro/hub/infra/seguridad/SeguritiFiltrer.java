package com.foro.hub.infra.seguridad;

import com.foro.hub.usuarios.IUsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // define un componenete
public class SeguritiFiltrer  extends OncePerRequestFilter{
	@Autowired
	private TokenService tokenService;
	@Autowired
	private IUsuarioRepositorio iUsuarioRepositorio;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// Obtener el token del header
		var authHeader = request.getHeader("Authorization");
		if (authHeader != null) {//por si llega basio el token
			var token = authHeader.replace("Bearer ", "");//remplaza el prefijo por nada
			var nombreUsuario = tokenService.getSubject(token); // extract username
			if (nombreUsuario != null) {
				// Token valido
				var usuario = iUsuarioRepositorio.findByLogin(nombreUsuario);
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
						usuario.getAuthorities()); // Forzamos un inicio de sesion
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);//friltro
	}
}
