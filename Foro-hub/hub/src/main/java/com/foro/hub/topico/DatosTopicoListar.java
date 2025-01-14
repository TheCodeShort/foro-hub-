package com.foro.hub.topico;

import java.time.LocalDateTime;

public record DatosTopicoListar(
                                String titulo,
                                String mensaje,
                                String curso) {



    /*public DatosTopicoListar (Topico topico){
			this(
					topico.getTitulo(),
					topico.getMensaje(),
					topico.getCurso());
		}*/
	}
