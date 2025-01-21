package com.foro.hub.infra.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.foro.hub.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("${api.security.secret}")
	private String apiSecreta;
	public String generarToken(Usuario usuario){
		try {
			Algorithm algorithm = Algorithm.HMAC256(apiSecreta);
			return JWT.create()
					.withIssuer("foro-hub")
					.withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId())
					.withExpiresAt(generarFechaExpirar())
					.sign(algorithm);
		} catch (JWTCreationException exception){
			throw new RuntimeException();
			}
	}
	private Instant generarFechaExpirar(){
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}
}
