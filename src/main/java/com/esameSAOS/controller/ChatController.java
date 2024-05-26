package com.esameSAOS.controller;

import com.esameSAOS.service.JwtTokenService;
import com.esameSAOS.service.OpenAiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ChatController {

	// Chiave segreta per firmare/verificare il JWT
	//private static final String JWT_SECRET = "segretoSuperSicuro";

	private final JwtTokenService service;

	@Autowired
	public ChatController(JwtTokenService service) {
		this.service = service;
	}

	@Autowired
	private OpenAiService openAiService;
	
	@GetMapping("/chat")
	public String openAiHandeller(@RequestParam("userInput") String userInput) {
		try {
			return openAiService.OpenAiSericeCall(userInput);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Errore durante l'elaborazione della richiesta JSON.";
		}
		
	}

}
