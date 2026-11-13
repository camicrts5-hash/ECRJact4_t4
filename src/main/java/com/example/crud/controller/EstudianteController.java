package com.example.crud.controller;

import com.example.crud.dto.EstudianteRequest;
import com.example.crud.dto.EstudianteResponse;
import com.example.crud.entity.Estudiante;
import com.example.crud.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EstudianteController
 */
@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    // GET Devuelve DTOs
    @GetMapping
    public ResponseEntity<List<EstudianteResponse>> getAll() {
        List<EstudianteResponse> lista = service.getEstudiantes().stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    //GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> getById(@PathVariable Long id) {
        return service.getEstudiante(id)
                .map(est -> ResponseEntity.ok(convertToDTO(est)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST 
    @PostMapping
    public ResponseEntity<EstudianteResponse> save(@Valid @RequestBody EstudianteRequest request) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(request.getNombre());
        estudiante.setApellido(request.getApellido());
        estudiante.setEmail(request.getEmail());

        Estudiante guardado = service.saveOrUpdate(estudiante);
        return new ResponseEntity<>(convertToDTO(guardado), HttpStatus.CREATED);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EstudianteResponse convertToDTO(Estudiante est) {
        EstudianteResponse dto = new EstudianteResponse();
        dto.setId(est.getId());
        dto.setNombre(est.getNombre());
        dto.setApellido(est.getApellido());
        dto.setEmail(est.getEmail());
        return dto;
    }
}