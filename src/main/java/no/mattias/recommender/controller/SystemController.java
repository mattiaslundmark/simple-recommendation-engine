package no.mattias.recommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.mattias.recommender.service.SparkService;

@RestController
public class SystemController {

    @Autowired
    SparkService sparkService;
    
    /**
     * Starts the training of the model. This should ideally run in the background.
     * @return 
     */
    @RequestMapping("/train")
    public String hello() {
        String result = sparkService.train();
        
        return result;
    }
}
