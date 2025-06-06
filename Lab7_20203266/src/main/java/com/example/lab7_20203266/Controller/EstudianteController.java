package com.example.lab7_20203266.Controller;

import com.example.lab7_20203266.Dto.EstudianteResponseDTO;
import com.example.lab7_20203266.Entity.Estudiante;
import com.example.lab7_20203266.Repository.EstudianteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @PostMapping
    public ResponseEntity<String> registrar(@Valid @RequestBody Estudiante est) {
        est.setEstado(true);
        est.setFechaRegistro(LocalDateTime.now());
        estudianteRepo.save(est);
        return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante registrado correctamente.");
    }

    @GetMapping
    public List<EstudianteResponseDTO> listar() {
        return estudianteRepo.findAll().stream().map(this::toDTO).toList();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Optional<Estudiante> opt = estudianteRepo.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(toDTO(opt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody Estudiante datos) {
        Optional<Estudiante> opt = estudianteRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");

        Estudiante est = opt.get();
        est.setCorreoPersonal(datos.getCorreoPersonal());
        est.setTelefono(datos.getTelefono());
        est.setUltimaActualizacion(LocalDateTime.now());
        estudianteRepo.save(est);

        return ResponseEntity.ok("Estudiante actualizado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarLogicamente(@PathVariable Integer id) {
        Optional<Estudiante> opt = estudianteRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");

        Estudiante est = opt.get();
        est.setEstado(false);
        estudianteRepo.save(est);
        return ResponseEntity.ok("Estudiante dado de baja (borrado lógico).");
    }

    private EstudianteResponseDTO toDTO(Estudiante e) {
        EstudianteResponseDTO dto = new EstudianteResponseDTO();
        BeanUtils.copyProperties(e, dto);
        dto.setEstado(e.getEstado() ? "Activo" : "Inactivo");
        return dto;
    }
}
