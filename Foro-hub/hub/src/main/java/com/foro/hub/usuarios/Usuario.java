package com.foro.hub.usuarios;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="Topico")
@Table(name="topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")

public class Usuario {


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String login;
		private String clave;

	}
