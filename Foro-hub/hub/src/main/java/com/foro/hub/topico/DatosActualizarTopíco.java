package com.foro.hub.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopíco(@NotNull Long id,
                                             String titulo,
		                                    String mensaje,
											String fechaCreacion,
											Boolean status,
		                                    String autor,
											String curso) {

}
