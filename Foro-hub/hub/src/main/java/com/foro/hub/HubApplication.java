package com.foro.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HubApplication.class, args);
	}

}

/*SELECT * FROM usuario
INSERT INTO usuario (login, clave) VALUES ('randy', '123456');

UPDATE usuario SET clave = 'hex: 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92

HEX: 8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92

h:e:x: 8d:96:9e:ef:6e:ca:d3:c2:9a:3a:62:92:80:e6:86:cf:0c:3f:5d:5a:86:af:f3:ca:12:02:0c:92:3a:dc:6c:92

base64: jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=

ALTER TABLE usuario ALTER COLUMN clave TYPE VARCHAR(500);*/