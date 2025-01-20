package com.foro.hub.topico;

import jakarta.validation.constraints.NotNull;

public record DatosRespuestaTopico(         Long id,
											String titulo,
											String mensaje,
											String fechaCreacion,
											Boolean status,
											String autor,
											String curso) {
											}
