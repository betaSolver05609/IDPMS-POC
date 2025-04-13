package com.example.idpms.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.idpms.Model.AuthenticationDTO;
import com.example.idpms.Model.RegisterResponseDTO;
import com.example.idpms.Service.UserService;
import com.google.gson.Gson;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("idp")
public class IDPController {

    @Autowired
    UserService service;

    @Autowired
    Gson gson;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, Object> payload) {
        String username = (String)payload.get("username");
        String password = (String)payload.get("password");
        RegisterResponseDTO process;
        try {
            process = service.processUserRegistration(username, password);
            String response=gson.toJson(process);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went Wrong");   
        }
    }
    @PostMapping("authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationDTO authenticationPayload) {
        String username = authenticationPayload.getUsername();
        System.out.println(username);
        BigInteger client_key = authenticationPayload.getClient_key();
        BigInteger A = authenticationPayload.getA();
        try {
            boolean auhenticationResponse = service.processUserAuthentication(username, client_key, A);
            if(auhenticationResponse) {
                return ResponseEntity.ok().body("Authentication Successfull");
            }   
            else {
                return ResponseEntity.status(401).body("Authentication Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}
