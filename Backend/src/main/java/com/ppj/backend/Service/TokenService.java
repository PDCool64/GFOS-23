/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class TokenService {
    private final String SECRET = "As7FA2df!-,.8Gg345ms/dh(65hj"; // TOPSECRET!
    private final long DT = 120000; // Token 120 Sekunden gültig
    
    public String createNewToken(String user) {
        try {
            long t = (System.currentTimeMillis() / DT) * DT;
            Algorithm algorithm = Algorithm.HMAC256(SECRET + t);
            String token = JWT.create()
                .withIssuer("GFOSProjekt")
                .withSubject(user)
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            return "JWT-Creation failed.";
        }
    }        
    
    public String verifyToken(String token) {
        try {
            long t = (System.currentTimeMillis() / DT) * DT;
            Algorithm algorithm = Algorithm.HMAC256(SECRET + t);
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withIssuer("GFOSProjekt")
                                      .build();
            DecodedJWT jwt = verifier.verify(token);
            return token; // das gültige Token wieder zurückgeben.
        } catch (JWTVerificationException ex1){            
            try { 
                // Wenn altes Token gerade (innerhalb eines Zeitfensters von
                // 2*DT) abgelaufen, automatisch um DT Milisekunden erneuern:
                long t = (System.currentTimeMillis() / DT) * DT - DT;
                Algorithm algorithm = Algorithm.HMAC256(SECRET + t);
                JWTVerifier verifier = JWT.require(algorithm)
                                          .withIssuer("GFOSProjekt")
                                          .build();
                DecodedJWT jwt = verifier.verify(token);
                return this.createNewToken(jwt.getSubject());
            } catch(JWTVerificationException ex2) {                
                return ""; // altes Token zu lange (> 2*DT) abgelaufen
            }
        }
    }
    
    public String getUser(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        }catch(JWTDecodeException exception){
            return "";
        }
    }
}	
     
    
