package com.foro.hub.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosTopicoListar(
                                @NotBlank String titulo,
                                @NotBlank String mensaje,
                                @NotBlank String curso) {

	}
