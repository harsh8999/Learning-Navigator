package com.harsh.learningnavigator.easteregg.services;

import java.util.Map;

/**
 * Service interface for generating number facts.
 */
public interface NumberFactService {

    /**
     * Generates a number fact for the given number.
     * 
     * @param number The number for which to generate the fact.
     * @return A map containing the generated fact, where the key is the number and the value is the fact.
     */
    Map<Integer, String> generateFact(int number);
    
}
