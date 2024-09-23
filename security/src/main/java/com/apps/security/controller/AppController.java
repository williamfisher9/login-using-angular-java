package com.apps.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/app")
public class AppController {

    @RequestMapping(method = RequestMethod.GET, path = "/personal")
    public ResponseEntity<Map<String, String>> personal(){
        Map<String, String> response = new HashMap<>();
        response.put("response", "PERSONAL COM");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
