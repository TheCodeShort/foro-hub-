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
	private String mensaje;
	private String curso;


	public Topico(String titulo, String mensaje, String curso) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.curso = curso;
	}

	public Topico(DatosTopicoListar datosTopicoListar){
		this.titulo = datosTopicoListar.titulo();
		this.curso = datosTopicoListar.curso();
		this.mensaje = datosTopicoListar.mensaje();
	}

}
