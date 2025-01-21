package com.foro.hub.controller;


import com.foro.hub.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController//para decirle que es un controller
@RequestMapping("/topicos")//para mapear el PAHT
public class TopicoController {

	@Autowired
	private ITopicoRepositorio iTopicoRepositorio;//para poder usar las funciones de JPA en esta clase

	@GetMapping//para traer la informacion
	//Pageable me ayuda a organizar los datos
	//@PageableDefault(size = 2) nos ayuda a organizar las busquedas pero personalizadas pero se puede modificar con la URL
	public ResponseEntity <Page<DatosTopicoListar>> obtenerTopicos(Pageable paginacion) {//con esto podemos traer cierta informacion mapeada para no mostrar todo
		//return iTopicoRepositorio.findAll(paginacion).map(DatosTopicoListar::new);
		return ResponseEntity.ok(iTopicoRepositorio.findByStatusTrue(paginacion).map(DatosTopicoListar::new));

	}


	@PostMapping//peticion http para enviar informacion
	//@RequestBody no ayuda a traer la informacion y es bueno espesificar que tipo de objeto se va a retorinar
	public ResponseEntity <DatosRespuestaTopico> topicos(@RequestBody @Valid DatosTopicoListar datosTopicoListar,
												UriComponentsBuilder uriComponentsBuilder){

		Optional<Topico> topicoExistente = iTopicoRepositorio.findByTituloAndMensaje(
				datosTopicoListar.titulo(),
				datosTopicoListar.mensaje()
		);

		// Si existe, retornar un 409
		if (topicoExistente.isPresent()) {
			return ResponseEntity.status(409)
					.body(null); // También puedes incluir un mensaje detallado si lo deseas
		}else {

			Topico topico = iTopicoRepositorio.save(new Topico(datosTopicoListar));
			DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
					topico.getId(),
					topico.getTitulo(),
					topico.getMensaje(),
					topico.getFechaCreacion(),
					topico.getStatus(),
					topico.getAutor(),
					topico.getCurso()
			);
			//aca creamos la url pero es la misma pero mas dinamica por eso se utilizo UriComponentsBuilder
			URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
			return ResponseEntity.created(url).body(datosRespuestaTopico);

		}

	}

	/*@PutMapping()//para actualizar inforacion
	@Transactional//finaliza el codigo pero guarda el proceso es como un commit
	public ResponseEntity ActualizarTopico(@RequestBody @Valid DatosActualizarTopíco datosActualizarTopíco){
		Topico topico = iTopicoRepositorio.getReferenceById(datosActualizarTopíco.id());//con esto obtenemos el ID
		topico.actualizarDatos(datosActualizarTopíco);//hasta aca se actualiza todo lo que queramos
		//finalmente se retorina toda la inforacion y como ya se actualizao pues nos muestra los datos actualizados
		return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),topico.getMensaje(),
																topico.getFechaCreacion(),topico.getStatus(),
																topico.getAutor(),topico.getCurso()));
	}*/

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
		// Verificar si el registro existe en la base de datos
		Optional<Topico> optionalTopico = iTopicoRepositorio.findById(id);

		if (optionalTopico.isEmpty()) {
			// Si el registro no existe, retorna un 404 Not Found
			return ResponseEntity.status(404)
					.body("El tópico no existe. Verifica el ID proporcionado.");
		}

		Topico topico = optionalTopico.get();

		// Validar si el tópico ya está marcado como eliminado (status = false)
		if (!topico.getStatus()) {
			// Si ya estaba eliminado, retorna un 400 Bad Request indicando esto
			return ResponseEntity.badRequest()
					.body("El tópico ya estaba eliminado previamente.");
		}

		// Marcar como eliminado (cambiar el estado a falso)
		topico.setStatus(false); // Aquí asumimos que tienes un setter para cambiar el estado

		// Retornar una respuesta indicando una correcta eliminación
		return ResponseEntity.ok("El tópico se eliminó correctamente.");
	}


	//retornar datos medico
	@GetMapping ("/{id}")//para hacerlo dinamico adcional se pone entre llavaes para decirle que hay va una variable
	@Transactional//finaliza el codigo pero guarda el proceso es como un commit
	public ResponseEntity <DatosRespuestaTopico> retornarPorId(@PathVariable Long id){
		Topico topico = iTopicoRepositorio.getReferenceById(id);
		var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),topico.getMensaje(),
													topico.getFechaCreacion(),topico.getStatus(),
													topico.getAutor(),topico.getCurso());

		return ResponseEntity.ok(datosTopico);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> actualizarTopicoIndependiente(@PathVariable Long id,
	                                                       @RequestBody @Valid DatosActualizarTopíco datosActualizarTopíco) {
		Topico topico = iTopicoRepositorio.getReferenceById(id);

		// Verificar si el título ya existe en la base de datos
		if (datosActualizarTopíco.titulo() != null && !datosActualizarTopíco.titulo().equals(topico.getTitulo())) {
			Optional<Topico> topicoConTitulo = iTopicoRepositorio.findByTituloAndMensajeAndIdNot(
					datosActualizarTopíco.titulo(),
					topico.getMensaje(),
					id
			);
			if (topicoConTitulo.isPresent()) {
				return ResponseEntity.status(409)
						.body(null);
			}
			topico.setTitulo(datosActualizarTopíco.titulo());
		}

		// Verificar si el mensaje ya existe en la base de datos
		if (datosActualizarTopíco.mensaje() != null && !datosActualizarTopíco.mensaje().equals(topico.getMensaje())) {
			Optional<Topico> topicoConMensaje = iTopicoRepositorio.findByTituloAndMensajeAndIdNot(
					topico.getTitulo(),
					datosActualizarTopíco.mensaje(),
					id
			);
			if (topicoConMensaje.isPresent()) {
				return ResponseEntity.status(409)
						.body(null);
			}
			topico.setMensaje(datosActualizarTopíco.mensaje());
		}

		// Verificar si el autor ya existe en otro registro
		if (datosActualizarTopíco.autor() != null && !datosActualizarTopíco.autor().equals(topico.getAutor())) {
			Optional<Topico> topicoConAutor = iTopicoRepositorio.findByAutorAndIdNot(
					datosActualizarTopíco.autor(),
					id
			);
			if (topicoConAutor.isPresent()) {
				return ResponseEntity.status(409)
						.body(null);
			}
			topico.setAutor(datosActualizarTopíco.autor());
		}

		// Verificar si el curso ya existe en otro registro
		if (datosActualizarTopíco.curso() != null && !datosActualizarTopíco.curso().equals(topico.getCurso())) {
			Optional<Topico> topicoConCurso = iTopicoRepositorio.findByCursoAndIdNot(
					datosActualizarTopíco.curso(),
					id
			);
			if (topicoConCurso.isPresent()) {
				return ResponseEntity.status(409)
						.body(null);
			}
			topico.setCurso(datosActualizarTopíco.curso());
		}

		// Guardar los cambios
		return ResponseEntity.ok(new DatosRespuestaTopico(
				topico.getId(),
				topico.getTitulo(),
				topico.getMensaje(),
				topico.getFechaCreacion(),
				topico.getStatus(),
				topico.getAutor(),
				topico.getCurso()
		));
	}





	//borrar pero en la base de datos
	/*public void eliminarTopico(@PathVariable Long id){// con esto decimo que el id biene de del path
		Topico topico = iTopicoRepositorio.getReferenceById(id);
		iTopicoRepositorio.delete(topico);
		DELETE FROM flyway_schema_history WHERE script = 'V2__alter-table-topicos-add-activo.sql';
	}*/
}

