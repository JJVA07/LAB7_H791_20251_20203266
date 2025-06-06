package com.example.lab7_20203266.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    @Pattern(regexp = "\\d{8}", message = "El código PUCP debe tener exactamente 8 dígitos")
    private String codigoPucp;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @Pattern(regexp = "M|F", message = "El sexo debe ser 'M' o 'F'")
    private String sexo;

    @Email(message = "Correo institucional inválido")
    @Pattern(regexp = ".*@pucp\\.edu\\.pe", message = "Debe ser dominio @pucp.edu.pe")
    private String correoInstitucional;

    @Email(message = "Correo personal inválido")
    private String correoPersonal;

    @Pattern(regexp = "9\\d{8}", message = "El teléfono debe tener 9 dígitos y empezar con 9")
    private String telefono;

    @Size(max = 100, message = "La dirección debe tener máximo 100 caracteres")
    private String direccion;

    private String departamento;
    private String provincia;
    private String carrera;

    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaActualizacion;
    private Boolean estado;
}