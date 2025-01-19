package com.foro.hub.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosTopicoListar(
								 Long id,
                                @NotBlank String titulo,
                                @NotBlank String mensaje,
								 String fechaCreacion,
								 Boolean status,
								 @NotBlank String  autor,
                                @NotBlank String curso) {

	public DatosTopicoListar(Topico topico){
		this(topico.getId(), topico.getTitulo(),
				topico.getMensaje(), topico.getFechaCreacion(),
				topico.getStatus(), topico.getAutor(),
				topico.getCurso());
	}

}
