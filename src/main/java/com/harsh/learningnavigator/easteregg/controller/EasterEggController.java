package com.harsh.learningnavigator.easteregg.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.learningnavigator.easteregg.services.NumberFactService;

@RestController
public class EasterEggController {
    private static final String URL = "/hidden-feature";

    private NumberFactService numberFactService;

    public EasterEggController(NumberFactService numberFactService) {
        this.numberFactService = numberFactService;
    }

    @GetMapping(URL + "/{number}")
    public ResponseEntity<Map<Integer, String>> numberFact(@PathVariable("number") int number) {
        return new ResponseEntity<>(numberFactService.generateFact(number), HttpStatus.OK);
    }
}
