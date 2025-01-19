package com.foro.hub.controller;


import com.foro.hub.topico.DatosActualizarTopíco;
import com.foro.hub.topico.DatosTopicoListar;
import com.foro.hub.topico.ITopicoRepositorio;
import com.foro.hub.topico.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//para decirle que es un controller
@RequestMapping("/topicos")//para mapear el PAHT
public class TopicoController {

	@Autowired
	private ITopicoRepositorio iTopicoRepositorio;//para poder usar las funciones de JPA en esta clase

	@GetMapping//para traer la informacion
	//Pageable me ayuda a organizar los datos
	//@PageableDefault(size = 2) nos ayuda a organizar las busquedas pero personalizadas pero se puede modificar con la URL
	public Page<DatosTopicoListar> obtenerTopicos(@PageableDefault(size = 2) Pageable paginacion) {//con esto podemos traer cierta informacion mapeada para no mostrar todo
		return iTopicoRepositorio.findAll(paginacion).map(DatosTopicoListar::new);
	}


	@PostMapping//peticion http para enviar informacion
	//@RequestBody no ayuda a traer la informacion
	public void topicos(@RequestBody @Valid DatosTopicoListar datosTopicoListar){
		iTopicoRepositorio.save(new Topico(datosTopicoListar));
	}

	@PutMapping()//para actualizar inforacion
	@Transactional//finaliza el codigo pero guarda el proceso es como un commit
	public void ActualizarTopico(@RequestBody @Valid DatosActualizarTopíco datosActualizarTopíco){
		Topico topico = iTopicoRepositorio.getReferenceById(datosActualizarTopíco.id());//con esto obtenemos el ID
		topico.actualizarDatos(datosActualizarTopíco);
	}
}

