package com.gabriel.examenAPI;

import com.gabriel.examenAPI.utilities.Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación Spring Boot y abre la documentación en un navegador web.
 */
@SpringBootApplication
public class ExamenApiApplication {
	/**
	 * Método principal que inicia la aplicación Spring Boot y abre la documentación en un navegador web.
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ExamenApiApplication.class, args);
		Utility.openDocumentation();
	}
}
