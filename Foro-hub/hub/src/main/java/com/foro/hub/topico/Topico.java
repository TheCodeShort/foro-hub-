package com.foro.hub.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name="Topico")
@Table(name="topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	private String mensaje;
	private String fechaCreacion;
	private Boolean status = true;//estado del topico
	private String autor;
	private String curso;


	public Topico(DatosTopicoListar datosTopicoListar){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		this.titulo = datosTopicoListar.titulo();
		this.mensaje = datosTopicoListar.mensaje();
		this.fechaCreacion = LocalDateTime.now().format(formatter);//fecha actual
		this.status = true;
		this.autor = datosTopicoListar.autor();
		this.curso = datosTopicoListar.curso();

	}

	public void actualizarDatos(DatosActualizarTopíco datosActualizarTopíco) {
		if (datosActualizarTopíco.titulo() != null){
			this.titulo = datosActualizarTopíco.titulo();
		}
		if(datosActualizarTopíco.mensaje() != null){
			this.mensaje = datosActualizarTopíco.mensaje();
		}
		if (datosActualizarTopíco.status() != null){
			this.status = datosActualizarTopíco.status();
		}

	}
}
