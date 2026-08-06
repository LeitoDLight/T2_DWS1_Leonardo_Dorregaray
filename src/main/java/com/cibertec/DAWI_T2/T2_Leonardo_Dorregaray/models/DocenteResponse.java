package com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocenteResponse {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String especialidad;
    private LocalDate fechaIngreso;
}