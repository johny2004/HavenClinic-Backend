package puj.web.clinicahaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import puj.web.clinicahaven.entity.Droga;
import puj.web.clinicahaven.entity.Tratamiento;
import puj.web.clinicahaven.entity.Veterinario;
import puj.web.clinicahaven.entity.mascota;
import puj.web.clinicahaven.servicio.VeterinarioService;
import puj.web.clinicahaven.servicio.drogaService;
import puj.web.clinicahaven.servicio.petService;
import puj.web.clinicahaven.servicio.tratamientoService;


@RestController
@RequestMapping("/tratamientos")
@CrossOrigin(origins = {"http://localhost:4200", "https://johny2004.github.io"})
public class tratamientoController {
    @Autowired
    petService mascotaService;

    @Autowired
    tratamientoService tratamientoService;

    @Autowired
    drogaService drogaService;

    @Autowired
    VeterinarioService VeterinarioService;

    //agregar tratamiento
    //localhost:8090/tratamientos/add/1/1/1
    @PostMapping("/add/{vetId}/{mascotaId}/{drogaId}")
    @Operation(summary = "agregar un tratamiento a una mascota")
    public ResponseEntity<mascota> newTreatment(Model model, @PathVariable("vetId") Long idVet, @PathVariable("mascotaId") Long idMascota, @PathVariable("drogaId") Long idDroga ,@RequestBody Tratamiento tratamiento ) {
        mascota mascota = mascotaService.findById(idMascota);
        Droga droga = drogaService.findById(idDroga);
        Veterinario veterinario = VeterinarioService.findById(idVet);
        Tratamiento newTratamiento = new Tratamiento(tratamiento.getFecha());

        if(!mascota.isEnTratamiento()){
            mascota.setEnTratamiento(true);
        }
       
        newTratamiento.setMascota(mascota);
        newTratamiento.setVeterinario(veterinario);
        newTratamiento.setDroga(droga);
        
        tratamientoService.add(newTratamiento);
        return ResponseEntity.ok(mascota);
    }

    //cambiar el estado del tratamiento de una mascota
    //localhost:8090/tratamientos/update/1/1
    @PutMapping("/update/{mascotaId}/{drogaId}")
    @Operation(summary = "modificar el tratamiento de una mascota")
    public ResponseEntity<mascota> alterTreatment(Model model, @PathVariable("mascotaId") Long idMascota, @PathVariable("drogaId") Long idDroga, @RequestBody Tratamiento tratamiento) {
        mascota mascota = mascotaService.findById(idMascota);
        List<Tratamiento> tratamientos = tratamientoService.findByPetId(idMascota);

        Tratamiento tratamientoMascota = tratamientos.get(tratamientos.size() - 1);

        tratamientoMascota.setDroga(drogaService.findById(idDroga));
        tratamientoService.add(tratamientoMascota);

        return ResponseEntity.ok(mascota);
    }

    //administrar el tratamiento de una mascota
    //localhost:8090/tratamientos/alter/1
    @PutMapping("/alter/{mascotaId}")
    @Operation(summary = "administrar el tratamiento de una mascota")
    public ResponseEntity<Droga> alter(Model model, @PathVariable("mascotaId") Long id) {
        mascota mascota = mascotaService.findById(id);
        Tratamiento tratamiento = mascota.getTratamiento().get((mascota.getTratamiento().size() - 1));
        Droga droga = tratamiento.getDroga();

        mascota.setEnTratamiento(false);

        droga.setUnidadesDisponibles(droga.getUnidadesDisponibles() - 1);
        droga.setUnidadesVendidas(droga.getUnidadesVendidas() + 1);
        drogaService.update(droga);
        mascotaService.update(mascota);
        
        return ResponseEntity.ok(droga);
    }

    //mostrar informacion del tratamiento
      //localhost:8090/tratamientos/info/1
      @GetMapping("/info/{id}")
      public Tratamiento infoTratamiento(@PathVariable("id") Long id) {
          return tratamientoService.findById(id);
      }

      //Historial medico de la mascota
        //localhost:8090/tratamientos/historial/1
        @GetMapping("/historial/{id}")
        @Operation(summary = "ver historial de la Mascota seleccionada")
        public List<Tratamiento> petHistory(Model model, @PathVariable("id") Long id) {
            List<Tratamiento> historial = tratamientoService.getHistorial(id);

            return  historial;
        }

        //Obtener el nombre de una droga a partir del id de un tratamiento
        //http://localhost:8090/tratamientos/droga/1
        @GetMapping("/droga/{id}")
        @Operation(summary = "Obtener el nombre de una droga a partir del id de un tratamiento")
        public Droga drogaNombre(Model model, @PathVariable("id") Long id){
            Tratamiento tratamiento = tratamientoService.findById(id);
            return drogaService.findByTratamientoId(tratamiento.getId());
        }
}
