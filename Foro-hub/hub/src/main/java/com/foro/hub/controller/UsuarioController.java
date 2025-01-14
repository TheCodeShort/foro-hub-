package com.foro.hub.controller;


import com.foro.hub.topico.DatosTopicoListar;
import org.springframework.web.bind.annotation.*;

@RestController//para decirle que es un controller
@RequestMapping("/topicos")//para mapear el PAHT
public class UsuarioController {

	@PostMapping//peticion http
	//@RequestBody no ayuda a traer la informacion
	public void medicos(@RequestBody DatosTopicoListar datosTopicoListar){
		System.out.printf("Recibiendo datos");
		System.out.println(datosTopicoListar);
	}
}

