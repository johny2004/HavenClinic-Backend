package puj.web.clinicahaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import puj.web.clinicahaven.dto.ClienteDTO;
import puj.web.clinicahaven.dto.ClienteMapper;
import puj.web.clinicahaven.dto.VeterinarioDTO;
import puj.web.clinicahaven.dto.VeterinarioMapper;
import puj.web.clinicahaven.entity.Cliente;
import puj.web.clinicahaven.entity.UserEntity;
import puj.web.clinicahaven.entity.Veterinario;
import puj.web.clinicahaven.repositorio.UserRepository;
import puj.web.clinicahaven.security.CustomUserDetailService;
import puj.web.clinicahaven.servicio.VeterinarioService;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = {"http://localhost:4200", "https://johny2004.github.io"})
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    //Prueba de manejao de autenticacion
@GetMapping("/details")
public ResponseEntity<VeterinarioDTO> buscarCliente(){
    Veterinario veterinario = veterinarioService.findByEmail(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );
    VeterinarioDTO veterinarioDTO= VeterinarioMapper.INSTANCE.convert(veterinario);
    if(veterinario == null){
        return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.BAD_REQUEST);
    }
        return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.OK);
}

    @GetMapping("/all")
    public List<Veterinario> getAllVeterinarios() {
        return veterinarioService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<VeterinarioDTO> getVeterinarioById(@PathVariable Long id) {
        Veterinario veterinario = veterinarioService.findById(id);
        VeterinarioDTO VeterinarioDTO = VeterinarioMapper.INSTANCE.convert(veterinario);
        if (veterinario == null) {
            return new ResponseEntity<VeterinarioDTO>(VeterinarioDTO, HttpStatus.BAD_REQUEST);
      
        }
        return new ResponseEntity<VeterinarioDTO>(VeterinarioDTO, HttpStatus.CREATED);
    }

      @GetMapping("/findByCedula/{cedula}")
    @Operation(summary = "find veterinario by cedula")
    public Veterinario getVeterinarioByCed(@PathVariable("cedula") int cedula) {
        Veterinario veterinario = veterinarioService.findVetByCedula(cedula);
        return veterinario; 
    }

    @PostMapping("/agregarVeterinario")
    public  ResponseEntity addVeterinario(@RequestBody Veterinario veterinario) {
        if(userRepository.existsByUsername(veterinario.getCorreo())){
        return new ResponseEntity<String>("Este veterinario ya esta registrado", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = customUserDetailService.VetToUser(veterinario);
        veterinario.setUserEntity(userEntity);
        Veterinario VeterinarioNuevo = veterinarioService.add(veterinario);
        VeterinarioDTO VeterinarioDTO = VeterinarioMapper.INSTANCE.convert(VeterinarioNuevo);

        if(VeterinarioNuevo == null){
            return new ResponseEntity<VeterinarioDTO>(VeterinarioDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<VeterinarioDTO>(VeterinarioDTO, HttpStatus.CREATED);

        /* 
       Veterinario newVeterinario = veterinarioService.add(veterinario);
       VeterinarioDTO VeterinarioDTO = VeterinarioMapper.INSTANCE.convert(newVeterinario);
        if(newVeterinario == null){
            return new ResponseEntity<VeterinarioDTO>(VeterinarioDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<VeterinarioDTO>(VeterinarioDTO, HttpStatus.CREATED);
       */
    }

    //localhost:8090/veterinario/update/1
    //se encarga de actualizar la info del vet
    @PutMapping("/update/{cedula}")
    public void updateVeterinario(@PathVariable("cedula") int cedula, @RequestBody Veterinario veterinarioDetails) {
        System.out.println("cedula cliente a actualizar: "+ cedula);
        Veterinario veterinario = veterinarioService.findVetByCedula(cedula);
        if (veterinario == null) {
            return;
        }

        veterinarioService.updateVet(veterinarioDetails);

    }

    @PutMapping("/cambiarEstado/{cedula}")
    public void cambiarEstadoVeterinario(@PathVariable("cedula") int cedula, @RequestBody Veterinario veterinarioDetails) {
        System.out.println("cedula cliente a eliminar: "+ cedula);
        Veterinario veterinario = veterinarioService.findVetByCedula(cedula);
        System.out.println("el veterinario es:"+ veterinario);
        if (veterinario == null) {
            return;
        }

        veterinarioService.cambiarEstado(veterinario);
        System.out.println("el veterinario esta:"+ veterinario.getActivo());
      

        return;
    }


   //no se usa
    //localhost:8090/veterinario/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVeterinario(@PathVariable Long id) {
        Veterinario veterinario = veterinarioService.findById(id);
        if (veterinario == null) {
            return ResponseEntity.notFound().build();
        }
        veterinarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //eliminar por cedula//en este caso no eliminar al vet sino que cambia su estado de activo a desactivado
    //localhost:8090/veterinario/eliminarVeterinario/1
    @DeleteMapping("/eliminarVeterinario/{cedula}")
public void EliminarVeterinario(@PathVariable("cedula") int cedula) {
    veterinarioService.deleteVetByCedula(cedula);

}


    //localhost:8090/veterinario/findEmail/pq@c.m
    @GetMapping("/findEmail/{correo}")
    public Veterinario MostrarInfoVet(@PathVariable("correo") String correo) {
        Veterinario veterinario = veterinarioService.findByEmail(correo);
        return veterinario;
    }

    @GetMapping("/findByNombre/{nombre}")
@Operation(summary = "Buscar veterinario por nombre")
public List<Veterinario> findByNombre(@PathVariable("nombre") String nombre) {
    return veterinarioService.findByNombre(nombre);
}
}