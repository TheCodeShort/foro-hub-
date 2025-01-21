package com.foro.hub.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITopicoRepositorio extends JpaRepository<Topico, Long> {

	Page<Topico> findByStatusTrue(Pageable paginacion);
	Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);
	Optional<Topico> findByAutorAndIdNot(String autor, Long id);
	Optional<Topico> findByCursoAndIdNot(String curso, Long id);
	Optional<Topico> findByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);


}
