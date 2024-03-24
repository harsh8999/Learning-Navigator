package com.harsh.learningnavigator.easteregg.services.implementations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harsh.learningnavigator.easteregg.services.NumberFactService;

@Service
public class NumberFactServiceImplementation implements NumberFactService {

    String NUMBER_GENERATOR_BASE_URL = "http://numbersapi.com/";

    private RestTemplate restTemplate;

    public NumberFactServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<Integer, String> generateFact(int number) {
        String url = NUMBER_GENERATOR_BASE_URL + "/" + number;

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if(result.getStatusCode().is2xxSuccessful()) {
            Map<Integer, String> fact = new HashMap<>();
            fact.put(number, result.getBody());
            return fact;
        } else {
            return Collections.emptyMap();
        }
    }
    
}
