package puj.web.clinicahaven.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import puj.web.clinicahaven.entity.Admin;
import puj.web.clinicahaven.entity.Cliente;
import puj.web.clinicahaven.entity.Veterinario;
import puj.web.clinicahaven.security.JWTGenerator;
import puj.web.clinicahaven.servicio.ClienteService;
import puj.web.clinicahaven.servicio.VeterinarioService;
import puj.web.clinicahaven.servicio.adminService;

@Controller
@CrossOrigin(origins = {"http://localhost:4200", "https://johny2004.github.io"})
public class PageController {

    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private adminService  adminService;

    @Autowired
    AuthenticationManager authenticationManager; 

    @Autowired
    JWTGenerator jwtGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
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
        if ("veterinario".equals(userType)) {
        System.out.println("email: " + email + " password: " + password + " userType: " + userType);}
        else if ("cliente".equals(userType)) {
            System.out.println("email: " + email + " password: " + password + " userType: " + userType);
        }
  
        if ("veterinario".equals(userType)) {
            Veterinario veterinario = veterinarioService.findByEmail(email);
            System.out.println("veterinario: " + veterinario.getCorreo());
            System.out.println("veterinario contrasena: " + veterinario.getContrasena());
            
            if ( veterinario.getCorreo().equals(email) && veterinario.getContrasena().equals(password)) {
                System.out.println("entro al veterinario: " + veterinario.getCorreo());
                 
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(veterinario.getCorreo(), password)

                );
    
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateToken(authentication);
                return new ResponseEntity<>(token, HttpStatus.OK);
                
            }
        } else if ("cliente".equals(userType)) {
            Cliente cliente = clienteService.findByEmail(email);
            System.out.println("cliente: " + cliente.getNombre());
            System.out.println("cliente contrasena: " + cliente.getContrasena());
            System.out.println("entro al cliente: " + cliente.getCorreo());
            if (cliente.getCorreo().equals(email) && cliente.getContrasena().equals(password)) {
                System.out.println("entro al cliente: " + cliente.getCorreo());
                

                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
                );
    
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateToken(authentication);
                
                return new ResponseEntity<>(token, HttpStatus.OK);
                
            }
                 
        }else if ("admin".equals(userType)) {
            Admin admin = adminService.findByCorreo(email);
            if ( admin.getCorreo().equals(email) && admin.getContrasena().equals(password)) {
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
                );
    
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateToken(authentication);
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        }
    
       
        return new ResponseEntity<>("Credenciales inválidas, vuelva a intentar", HttpStatus.UNAUTHORIZED);
    }



}