package com.example.crud.service;

import com.example.crud.entity.Estudiante;
import com.example.crud.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * EstudianteService
 */
@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Page<Estudiante> getEstudiantes(Pageable pageable) {
        return estudianteRepository.findAll(pageable);
    }

    public Optional<Estudiante> getEstudiante(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante saveOrUpdate(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante update(Long id, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudiante.setNombre(estudianteActualizado.getNombre());
            estudiante.setApellido(estudianteActualizado.getApellido());
            estudiante.setEmail(estudianteActualizado.getEmail());
            return estudianteRepository.save(estudiante);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id " + id));
    }

    public void delete(Long id) {
        estudianteRepository.deleteById(id);
    }
}