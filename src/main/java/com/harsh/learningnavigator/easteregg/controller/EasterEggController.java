package com.harsh.learningnavigator.easteregg.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.learningnavigator.easteregg.services.NumberFactService;

/**
 * Controller class for handling requests related to the hidden Easter Egg feature.
 */
@RestController
public class EasterEggController {

    /** Base URL path for the Easter Egg feature. */
    private static final String URL = "/hidden-feature";

    /** Service for generating number facts. */
    private NumberFactService numberFactService;

    /**
     * Constructs a new EasterEggController with the given NumberFactService.
     * 
     * @param numberFactService The NumberFactService instance to use.
     */
    public EasterEggController(NumberFactService numberFactService) {
        this.numberFactService = numberFactService;
    }

    /**
     * Endpoint to retrieve a number fact for a given number.
     * 
     * @param number The number for which to generate the fact.
     * @return ResponseEntity containing a map with the generated fact and HTTP status OK.
     */
    @GetMapping(URL + "/{number}")
    public ResponseEntity<Map<Integer, String>> numberFact(@PathVariable("number") int number) {
        return new ResponseEntity<>(numberFactService.generateFact(number), HttpStatus.OK);
    }
}
