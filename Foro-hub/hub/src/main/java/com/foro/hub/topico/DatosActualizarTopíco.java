package com.foro.hub.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopíco(@NotNull Long id,
                                    @NotBlank String titulo,
                                    @NotBlank String mensaje,
									@NotBlank String status) {

}
