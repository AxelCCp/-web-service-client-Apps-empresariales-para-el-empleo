package com.devpredator.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.devpredator.model.dto.EmpleadoDto;

/**
 * @author Axel
 * Clase que permite consumir wl web service de Empleados
 */
public class EmpleadoWsClient {
	
	/**
	 * 
	 * @param zzz
	 */
	public static void main(String[]zzz) {
		
		//::::::::::::GET::::::::::::::
		/*
		Client client = ClientBuilder.newClient(); 																				//Client : clase principal de jersey que es para conectar con un servicio web local o remoto. 
		WebTarget webTarget = client.target("http://localhost:8080/project-web-services/devpredator/empleados-ws").path("consultar-empleado-num").path("12345");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if(response.getStatus() == 204) {
			System.out.println("No se encontró el registro con el número de empleado.");
		}
		
		if(response.getStatus() == 200) {	
			EmpleadoDto empleadoDto = response.readEntity(EmpleadoDto.class);
			System.out.println("Empleado: " + empleadoDto.getNombre());
			System.out.println("Empleado - fecha creacion: " + empleadoDto.getFechaCreacion());
		}
		*/
		
		
		Client client = ClientBuilder.newClient(); 																				
		WebTarget webTarget = client.target("http://localhost:8080/project-web-services/devpredator/empleados-ws").path("guardar-empleado");
		EmpleadoDto empleadoDto = new EmpleadoDto();
		empleadoDto.setNumEmpleado("7777");
		empleadoDto.setNombre("Robot Octavio");
		empleadoDto.setApellido1("RR");
		empleadoDto.setApellido2("... . ");
		empleadoDto.setEdad(20);
		empleadoDto.setFechaCreacion(LocalDateTime.now());
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);						//define el tipo de peticion q se quierre realizar al web service.				
		Response response = invocationBuilder.post(Entity.entity(empleadoDto, MediaType.APPLICATION_JSON));			//como vamos a consumir el web service,  con un json. se pone el obj que se va a mandar en la peticion post.  
		
		if(response.getStatus() == 400) {
			String error = response.readEntity(String.class);
			System.out.println(error);
		}
		
		if(response.getStatus() == 200) {
			EmpleadoDto empleadoDto2 = response.readEntity(EmpleadoDto.class);
			System.out.println(empleadoDto2.getNombre());
		}
		
		
	}

}
