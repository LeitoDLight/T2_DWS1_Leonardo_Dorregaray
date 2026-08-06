package com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.repositories;

import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    // Query Method
    List<Docente> findByEspecialidadIgnoreCase(String especialidad);

    // Query Method
    List<Docente> findByApellidosContainingIgnoreCase(String apellidos);

    // JPQL
    @Query("SELECT d FROM Docente d WHERE d.correo = :correo")
    Optional<Docente> buscarPorCorreo(@Param("correo") String correo);
}