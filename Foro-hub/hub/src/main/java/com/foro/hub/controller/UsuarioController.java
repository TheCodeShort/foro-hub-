package com.foro.hub.controller;


import com.foro.hub.topico.DatosTopicoListar;
import com.foro.hub.topico.ITopicoRepositorio;
import com.foro.hub.topico.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController//para decirle que es un controller
@RequestMapping("/topicos")//para mapear el PAHT
public class UsuarioController {

	@Autowired
	private ITopicoRepositorio iTopicoRepositorio;//para poder usar las funciones de JPA en esta clase

	@PostMapping//peticion http
	//@RequestBody no ayuda a traer la informacion
	public void medicos(@RequestBody DatosTopicoListar datosTopicoListar){
		iTopicoRepositorio.save(new Topico(datosTopicoListar));
	}
}

