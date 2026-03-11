package puj.web.clinicahaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import jakarta.servlet.http.HttpSession;
import puj.web.clinicahaven.entity.Cliente;
import puj.web.clinicahaven.entity.SessionUtil;
import puj.web.clinicahaven.entity.mascota;
import puj.web.clinicahaven.servicio.ClienteService;
import puj.web.clinicahaven.servicio.petService;



@RestController
@RequestMapping("/mascotas")
@CrossOrigin(origins = {"http://localhost:4200", "https://johny2004.github.io"})
public class petController {
    @Autowired
    petService mascotaService;

    @Autowired
    ClienteService clienteService;

    

    //Mostrar todas las mascotas para el veterinario
    //localhost:8090/mascotas/vetmascota
    @GetMapping("/vetmascota")
    public ResponseEntity<List<mascota>> listPage() {
        List<mascota> lista = mascotaService.findAll();

        ResponseEntity<List<mascota>> response = new ResponseEntity<>(lista, HttpStatus.OK);
        return response;
    }


    //mascotas de cada cliente por id
    //localhost:8090/mascotas/mascotascliente/1
    @GetMapping("/mascotascliente/{id}")
    public ResponseEntity<List<mascota>> Mascotacliente(@PathVariable("id") Long id) {
        List<mascota> lista = mascotaService.findByDueñoId(id);

        ResponseEntity<List<mascota>> response = new ResponseEntity<>(lista, HttpStatus.OK);
        return response;
    }
      
    //mascotas del cliente loggeado (no se usa por ahora)
    //localhost:8090/mascotas/mis_mascotas
    @GetMapping("/mis_mascotas")
    public ResponseEntity<List<mascota>> showClientPets( HttpSession session) {
        Cliente loggedInClient = SessionUtil.getLoggedInClient(session);
        System.out.println("Cliente loggeado: " + loggedInClient.getNombre() + "id: " + loggedInClient.getClienteId());
        List<mascota> lista = mascotaService.findByDueñoId(loggedInClient.getClienteId());

        ResponseEntity<List<mascota>> response = new ResponseEntity<>(lista, HttpStatus.OK);
        return response;
    }

    //registrar mascota nueva
    //localhost:8090/mascotas/addPet
    @PostMapping("/addPet/{id}")
    public ResponseEntity<mascota> addPet(@PathVariable("id") int id,@RequestBody mascota mascota){
        Cliente cliente = clienteService.findByCedula(id);
        mascota.setDueño(cliente);
        mascotaService.agregar(mascota);
            
        return new ResponseEntity<>(mascota, HttpStatus.CREATED);
    }

    //Actualizar Mascota
    //localhost:8090/mascotas/actualizar_mascota/1
    @PutMapping("/actualizar_mascota/{id}")
    @Operation(summary = "actualizar la informacion de la Mascota seleccionada")
    public ResponseEntity<mascota> actualizarMascota(@RequestBody mascota mascota, @PathVariable("id") Long id) {
        mascota existingMascota = mascotaService.findById(id);
        if (existingMascota == null) {
            return new ResponseEntity<>(mascota, HttpStatus.NOT_FOUND);
        }
        mascota.setDueño(existingMascota.getDueño());
        mascotaService.update(mascota);

        return new ResponseEntity<>(mascota, HttpStatus.OK);
    }

    //Eliminar mascota veterinario
    //localhost:8090/mascotas/deletePet/2
    @DeleteMapping("/deletePet/{id}")
    @Operation(summary = "Eliminar la Mascota seleccionada")
    public ResponseEntity<String> deletePetVet(@PathVariable("id") Long id) {
        mascotaService.deleteById(id);
        
        return new ResponseEntity<>("DELETED", HttpStatus.NO_CONTENT);
    }
    
    //ver informacion de la mascotas
    //localhost:8090/mascotas/petInfo/1
    @GetMapping("/petInfo/{id}")
    @Operation(summary = "ver la Mascota seleccionada")
    public ResponseEntity<mascota> petInfo(Model model, @PathVariable("id") Long id) {
        mascota mascota = mascotaService.findById(id);

        if(mascota == null){
            return new ResponseEntity<>(mascota, HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(mascota, HttpStatus.OK);
    }

    //ver informacio de la mascota pero por nombre, para la barra de busqueda
    @GetMapping("/findByNombre/{nombre}")
    @Operation(summary = "ver la Mascota a buscar")
    public ResponseEntity<List<mascota>> findByNombre(@PathVariable("nombre") String nombre) {
        List<mascota> lista =  mascotaService.findByNombre(nombre);
                
        ResponseEntity<List<mascota>> response = new ResponseEntity<>(lista, HttpStatus.OK);
        return response;
    }

    //Mostrar todas las mascotas que el veterinario esta tratando
    //localhost:8090/mascotas/pacientes/1
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<List<mascota>> vetPatients(@PathVariable("id") Long id) {
        List<mascota> lista = mascotaService.getVetPets(id);

        ResponseEntity<List<mascota>> response = new ResponseEntity<>(lista, HttpStatus.OK);
        return response;
    }
}