package com.example.lab7_20203266.Repository;

import com.example.lab7_20203266.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByDni(String dni);
}
