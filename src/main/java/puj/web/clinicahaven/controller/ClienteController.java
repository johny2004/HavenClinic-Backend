package puj.web.clinicahaven.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import puj.web.clinicahaven.dto.ClienteDTO;
import puj.web.clinicahaven.dto.ClienteMapper;

import puj.web.clinicahaven.entity.Cliente;
import puj.web.clinicahaven.entity.UserEntity;
import puj.web.clinicahaven.repositorio.UserRepository;
import puj.web.clinicahaven.security.CustomUserDetailService;

import puj.web.clinicahaven.servicio.ClienteService;





@RestController   //pasa de controller a restcontroller
@RequestMapping("/cliente")
@CrossOrigin (origins = {"http://localhost:4200", "https://johny2004.github.io"}) //especifica al backend que paginas le pueden hacer peticiones//conecta con angular
public class ClienteController {
    
@Autowired
ClienteService clienteService;

@Autowired
UserRepository userRepository;

@Autowired
private CustomUserDetailService customUserDetailService;

@Autowired
AuthenticationManager authenticationManager;

UserEntity userEntity;

//Prueba de manejao de autenticacion
@GetMapping("/details")
public ResponseEntity<ClienteDTO> buscarCliente(){
    Cliente cliente = clienteService.findByEmail(
        SecurityContextHolder.getContext().getAuthentication().getName()

    );
    ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(cliente);
    if(cliente == null){
        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.BAD_REQUEST);
    }
        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
}



//menu principal del cliente
//localhost:8090/cliente/menu
@GetMapping("/menu")
public String getMenu(Model model, @ModelAttribute("cliente") Cliente cliente) {
  // Agregar las mascotas del cliente al modelo
  model.addAttribute("mascotas", cliente.getMascotas());
  return "mainMenu";
}

//localhost:8090/cliente/all
//usada para ver a todos los clientes
@GetMapping("/all")
public List<Cliente> MostrarEstudiantes(Model model) {
    //model.addAttribute("clientes", clienteService.findAll());
   // return "mostrar_todos_los_clientes";
   return clienteService.findAll();
}


//localhost:8090/cliente/find/10
//usada en el cliente/all para ver la info de un cliente por su cedula
@GetMapping("/find/{cedula}")
@Operation(summary = "find client by cedula number")
public Cliente MostrarInfoCliente(@PathVariable("cedula") int cedula) {
    Cliente client = clienteService.findByCedula(cedula);
    return client;  
}

//localhost:8090/cliente/findEmail/pq@c.m
@GetMapping("/findEmail/{correo}")
public Cliente MostrarInfoCliente(@PathVariable("correo") String correo) {
    Cliente cliente = clienteService.findByEmail(correo);
    return cliente;
}

//registra al cliente (no se uso, se dejo como pop up de index) cuando se paso a angular dejo de usarse
//localhost:8090/cliente/registrar

@GetMapping("/registrar") 
public String CrearNuevoCliente(Model model) {
    Cliente cliente = new Cliente ("", 0, 0, "", "");
    model.addAttribute("cliente", cliente);
    //model.addAttribute("mascotas", mascotas);
    return "registro_cliente";
}
//agrega el cliente despues de registrarse
//localhost:8090/cliente/agregarCliente
@PostMapping("/agregarCliente")
public ResponseEntity agregarCliente(@RequestBody Cliente cliente) {
    if(userRepository.existsByUsername(cliente.getCorreo())){
        return new ResponseEntity<String>("Este correo ya esta registrado", HttpStatus.BAD_REQUEST);
    }

    UserEntity userEntity = customUserDetailService.ClienteToUser(cliente);
    cliente.setUserEntity(userEntity);
    clienteService.add(cliente);

    return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);

    /* 
    // HttpSession session
   clienteService.add(cliente);
    //SessionUtil.setLoggedInClient(session, cliente);*/
}

//localhost:8080/cliente/eliminarCliente/{cedula}
//path variable para mandar el parametro de la url a la base de datos
@DeleteMapping("/eliminarCliente/{cedula}")
public ResponseEntity<String> eliminarCliente(@PathVariable("cedula") int cedula) {
    Cliente cliente = clienteService.findByCedula(cedula);

    if (cliente == null) {
        return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
    }
    clienteService.delete(cliente);
    System.out.println("Cliente eliminado con éxito"+HttpStatus.OK);
    return new ResponseEntity<String>("Cliente eliminado con éxito", HttpStatus.OK);
}

//actualizar cliente
//localhost:8080/cliente/update/1
@PutMapping("/update/{id}")
public ResponseEntity<ClienteDTO> actualizarCliente(HttpSession session, @RequestBody Cliente cliente, @PathVariable("id") Long id) {
        Cliente existingCliente = clienteService.findByid(id);
        ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(existingCliente);
        if (existingCliente == null) {
             return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.BAD_REQUEST);

        }
        clienteService.update(cliente);
        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
    }

    //para la barra de busqueda, ver la informacion del cliente por nombre
    @GetMapping("/findClienteByNombre/{nombre}")
    public List<Cliente> findByNombre(@PathVariable("nombre") String nombre) {
        return clienteService.findClienteByNombre(nombre);
    }


    //NO SIRVE NO SE USA//ERA PARA PROBAR EL LOGIN SOLO CLIENTE
     @PostMapping("/login")
     public ResponseEntity loginCliente (@RequestBody Cliente cliente) {

                /*  */
                Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(cliente.getCorreo(), "123")
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity("Usuario ingreso con exito", HttpStatus.OK);
         
     }
     
     
}