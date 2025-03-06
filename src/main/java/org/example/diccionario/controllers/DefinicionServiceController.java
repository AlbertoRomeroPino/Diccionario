package org.example.diccionario.controllers;

import org.example.diccionario.exceptions.RecordNotFoundException;
import org.example.diccionario.models.Definicion;
import org.example.diccionario.repositories.DefinicionRepository;
import org.example.diccionario.services.DefinicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/definicion")
public class DefinicionServiceController {

    @Autowired
    DefinicionService service;

    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinicion(@PathVariable Long id)
            throws RecordNotFoundException {
        service.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Definicion> createDefinicion(@RequestBody Definicion definicion, @PathVariable Long id){
        Definicion nuevaDefinicion = service.createDefinicion(definicion, id);

        return ResponseEntity.ok(nuevaDefinicion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Definicion>> palabraDefinicion(@PathVariable Long id) throws RecordNotFoundException {
        List<Definicion> definicion = service.getAllDefinicion(id);

        return ResponseEntity.ok(definicion);
    }

}
