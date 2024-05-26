package com.esameSAOS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.jayway.jsonpath.JsonPath;
import com.esameSAOS.OpenAiRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.esameSAOS.Message;
import java.util.*;

@Service
public class OpenAiService {

	@Autowired
	private RestTemplate restTemplate;

	
	@Value("${API_KEY}")
	private String API_KEY;
	
	@Value("${MODEL_ID}")
	private String MODEL_ID;
	
	@Value("${URL}")
	private String URL;

	public String OpenAiSericeCall(String userInput)  throws JsonProcessingException{

		OpenAiRequest request = new OpenAiRequest();
		request.setModel(MODEL_ID);

		Message mex = new Message("user", userInput);

		List<Message> messages = new ArrayList<>();

		messages.add(mex);
		request.setMessages(messages);

		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(request);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + API_KEY);

		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		String response = restTemplate.postForObject(URL, requestEntity, String.class);

		return   parseResponse(response);
		
	}
	
	  private String parseResponse(String jsonResponse) {
	        try {
	            String content = JsonPath.read(jsonResponse, "$.choices[0].message.content");
	            return content;
	        } catch (Exception e) {
	            e.printStackTrace();
				return "Errore nell'analisi della risposta JSON.";
	        }

	    }

}
