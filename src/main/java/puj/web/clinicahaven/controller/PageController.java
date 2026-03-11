package puj.web.clinicahaven.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import puj.web.clinicahaven.entity.Cliente;
import puj.web.clinicahaven.security.JWTGenerator;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "https://johny2004.github.io"})
public class PageController {

    @Autowired
    AuthenticationManager authenticationManager; 

    @Autowired
    JWTGenerator jwtGenerator;

    
    @GetMapping("/menu")
    public String getMenu() {
        return "mainMenu";
    }

    @GetMapping("/")
       public String index(Model model) {
        model.addAttribute("cliente", new Cliente()); // Agrega el objeto cliente al modelo
        return "index";
    }



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("psw");
        String userType = loginData.get("userType");
        
        System.out.println("Intento de login - email: " + email + " userType: " + userType);
        
        try {
            // Intentar autenticar con Spring Security (que usa BCrypt internamente)
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            
            System.out.println("Autenticación exitosa para: " + email);
            return new ResponseEntity<>(token, HttpStatus.OK);
            
        } catch (Exception e) {
            System.out.println("Error de autenticación para " + email + ": " + e.getMessage());
            return new ResponseEntity<>("Credenciales inválidas, vuelva a intentar", HttpStatus.UNAUTHORIZED);
        }
    }



}