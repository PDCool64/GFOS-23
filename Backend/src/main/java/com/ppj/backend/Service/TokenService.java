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
import com.ppj.backend.Entity.Account;
import com.ppj.backend.Facades.AccountFacade;

import jakarta.ejb.Stateless;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;



/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class TokenService {
    private final String SECRET = "R{,hA:>xYSPLvN~>R\\pz*3feN7EgpM`kfF1e=^22AxnpPI"; // TOPSECRET! Wenn jemand das hier liest, dann sind wir rechtlich am Arsch.
    private final long DT = 120_000; // Token 120 Sekunden gültig
    
    @EJB
    private AccountFacade accountFacade;

    public String createNewToken(String email) {
        try {
            long t = (System.currentTimeMillis() / DT) * DT;
            Algorithm algorithm = Algorithm.HMAC256(SECRET + t);
            String token = JWT.create()
                .withIssuer("GFOSProjekt")
                .withSubject(email)
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
            verifier.verify(token);
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
    
    public Account getUser(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return accountFacade.getAccountByEmail(jwt.getSubject());
        }catch(JWTDecodeException exception){
            return null; 
        }
    }
}	
     
    
