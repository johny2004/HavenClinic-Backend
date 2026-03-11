package puj.web.clinicahaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import puj.web.clinicahaven.entity.Droga;
import puj.web.clinicahaven.servicio.drogaService;

@RestController
@RequestMapping("/drogas")
@CrossOrigin(origins = {"http://localhost:4200", "https://johny2004.github.io"})
public class drogaController {
    @Autowired
    drogaService drogaService; 
    
    //Mostrar todas las drogas
    //localhost:8090/drogas/all
    @GetMapping("/all")
    public List<Droga> listaDrogas() {
        return drogaService.findAll();
    }


    //Mostrar informacion de una droga
    //localhost:8090/drogas/info/1
    @GetMapping("/info/{id}")
    public Droga infoDroga(@PathVariable("id") Long id) {
        return drogaService.findById(id);
    }

    //Obtener cantidad de drogas disponibles
    //localhost:8090/drogas/quantity/1
    @GetMapping("/quantity/{id}")
    public Number cantidadDroga(@PathVariable("id") Long id) {
        return drogaService.getQuantity(id);
    }

    //Buscar por nombre
    //http://localhost:8090/drogas/nombre/ACOLAN
    @GetMapping("/nombre/{nombre}")
    public Droga drogaNombre(@PathVariable("nombre") String nombre) {
        return drogaService.findByName(nombre);
    }
}
