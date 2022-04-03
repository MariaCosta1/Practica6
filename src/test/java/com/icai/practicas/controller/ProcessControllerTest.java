package com.icai.practicas.controller;

import com.icai.practicas.controller.ProcessController.DataRequest;
import com.icai.practicas.controller.ProcessController.DataResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void Test1OK() {

        
        String address = "http://localhost:" + port + "/api/v1/process-step1";

       
        DataRequest dato = new DataRequest("María Costa", "54490485N", "612345678"); 
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dato, headers); //Request

        
		ResponseEntity<DataResponse> respuesta = this.restTemplate.postForEntity(address, request, DataResponse.class); 

        
	
		DataResponse respuesta_esperada = new DataResponse("OK"); //si los datos estan bien, devuelve ok

		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(respuesta_esperada, respuesta.getBody()); 
    }

    @Test
    public void Test1KO() {

     
        String address = "http://localhost:" + port + "/api/v1/process-step1";

       
        DataRequest dato = new DataRequest("María Costa", "54490", "612345"); 
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dato, headers); //Request

        
		ResponseEntity<DataResponse> respuesta = this.restTemplate.postForEntity(address, request, DataResponse.class); 

        
	
		DataResponse respuesta_esperada = new DataResponse("KO"); 

		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(respuesta_esperada, respuesta.getBody()); 
    }

    @Test
    public void Test2OK() {
        
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        
        MultiValueMap<String, String> dato = new LinkedMultiValueMap<String, String>(); 
        dato.add("fullName", "María Costa");
        dato.add("dni", "54490485N");
        dato.add("telefono", "612345678"); 
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(dato, headers); 

        
        ResponseEntity<String> respuesta = this.restTemplate.postForEntity(address, request, String.class);

		
		String respuesta_esperada = ResponseHTMLGenerator.message1; //OK Message

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(respuesta_esperada, respuesta.getBody());
        
    }

    @Test
    public void processDataLegacyTestKO() {
        
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        
        MultiValueMap<String, String> dato = new LinkedMultiValueMap<String, String>(); 
        dato.add("fullName", "María Costa");
        dato.add("dni", "544904");
        dato.add("telefono", "6123-56"); 
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(dato, headers); 

        
        ResponseEntity<String> respuesta = this.restTemplate.postForEntity(address, request, String.class); 

		
		String respuesta_esperada = ResponseHTMLGenerator.message2;  //KO Message
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(respuesta_esperada, respuesta.getBody());
    }
}