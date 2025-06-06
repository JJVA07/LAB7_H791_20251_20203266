package com.example.lab7_20203266.Dto;

import lombok.Data;

@Data
public class EstudianteResponseDTO {
    private String nombres;
    private String apellidos;
    private String dni;
    private String codigoPucp;
    private String correoInstitucional;
    private String correoPersonal;
    private String telefono;
    private String direccion;
    private String departamento;
    private String provincia;
    private String carrera;
    private String sexo;
    private String estado;  // "Activo" o "Inactivo"
}
