package com.foro.hub.controller;

import com.foro.hub.infra.seguridad.DatosJWTToken;
import com.foro.hub.infra.seguridad.TokenService;
import com.foro.hub.usuarios.DatosAutenticacionUsuarios;
import com.foro.hub.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuarios datosAutenticacionUsuarios) {
		Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuarios.login(), datosAutenticacionUsuarios.clave());
		var usuarioAuntenticado = authenticationManager.authenticate(authToken);
		var JWTtoken = tokenService.generarToken((Usuario) usuarioAuntenticado.getPrincipal());
		return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
	}
}