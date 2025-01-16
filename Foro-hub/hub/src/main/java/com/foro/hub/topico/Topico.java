package com.foro.hub.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name="Topico")
@Table(name="topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	private String mensaje;
	private String curso;


	public Topico(DatosTopicoListar datosTopicoListar){
		this.titulo = datosTopicoListar.titulo();
		this.curso = datosTopicoListar.curso();
		this.mensaje = datosTopicoListar.mensaje();
	}

}
