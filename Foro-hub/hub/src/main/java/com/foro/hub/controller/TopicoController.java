package com.foro.hub.controller;


import com.foro.hub.topico.DatosTopicoListar;
import com.foro.hub.topico.ITopicoRepositorio;
import com.foro.hub.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//para decirle que es un controller
@RequestMapping("/topicos")//para mapear el PAHT
public class TopicoController {

	@Autowired
	private ITopicoRepositorio iTopicoRepositorio;//para poder usar las funciones de JPA en esta clase

	@PostMapping//peticion http
	//@RequestBody no ayuda a traer la informacion
	public void topicos(@RequestBody @Valid DatosTopicoListar datosTopicoListar){
		iTopicoRepositorio.save(new Topico(datosTopicoListar));
	}

	@GetMapping
	public void Topico (DatosTopicoListar datosTopicoListar){
		System.out.println(datosTopicoListar);
	}

}

