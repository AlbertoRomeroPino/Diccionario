package org.example.diccionario.controllers;

import jakarta.validation.Valid;
import org.example.diccionario.exceptions.RecordNotFoundException;
import org.example.diccionario.models.Definicion;
import org.example.diccionario.models.Palabra;
import org.example.diccionario.services.DefinicionService;
import org.example.diccionario.services.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/palabra")
public class PalabraServiceController {

    @Autowired
    PalabraService palabraService;
    @Autowired
    DefinicionService definicionService;

    @GetMapping
    public ResponseEntity<List<Palabra>> getAllPalabras() {
        List<Palabra> palabras = palabraService.getAllPalabras();

        List<Palabra> palabrasSinDefiniciones = palabras.stream()
                .map(palabra -> {
                    palabra.setDefinicions(null);
                    return palabra;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(palabrasSinDefiniciones, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabra(@PathVariable Long id)
            throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(id);
        palabra.setDefinicions(null);
        return new ResponseEntity<Palabra>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra nuevaPalabra = palabraService.createPalabra(palabra);
        return ResponseEntity.ok(nuevaPalabra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updatePalabra(@PathVariable Long id, @RequestBody Palabra palabra)
            throws RecordNotFoundException {
        palabra.setId(id);
        Palabra updatedPalabra = palabraService.updatePalabra(palabra);
        return ResponseEntity.ok(updatedPalabra);
    }

    // Nunca dejarlo bacio porque se borra toda la base de datos (Informacion)
    @DeleteMapping("/{id}")
    public HttpStatus deletePalabra(@PathVariable Long id)
            throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> inicialPalabras(@PathVariable String letra) {
        List<Palabra> palabraList = palabraService.getByInitial(letra);

        return ResponseEntity.ok(palabraList);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> categoriaPalabras(@PathVariable String categoria) {
        List<Palabra> palabraList = palabraService.getByCategoria(categoria);

        return ResponseEntity.ok(palabraList);
    }

    @PostMapping("/con-definicion")
    public ResponseEntity<Palabra> palabraConDefinicion(@RequestBody Palabra palabra) {
        Palabra newPalabra = palabraService.createPalabraDefinicion(palabra);

        return ResponseEntity.ok(newPalabra);
    }
}
