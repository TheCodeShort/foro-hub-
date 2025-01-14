package com.foro.hub.topico;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name="Topico")
@Table(name="topicos")
@Getter
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	public Topico(String titulo, String mensaje, String curso) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.curso = curso;
	}

	private String mensaje;
	private String curso;




}
