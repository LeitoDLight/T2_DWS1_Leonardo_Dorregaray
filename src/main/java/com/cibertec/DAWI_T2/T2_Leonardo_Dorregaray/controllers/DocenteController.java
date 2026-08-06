package com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.controllers;

import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.models.DocenteRequest;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.models.DocenteResponse;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.services.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")
@Tag(name = "Docentes", description = "Operaciones relacionadas con docentes")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los docentes")
    public ResponseEntity<List<DocenteResponse>> obtenerDocentes() {
        return ResponseEntity.ok(docenteService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un docente por ID")
    public ResponseEntity<DocenteResponse> obtenerDocentePorId(@PathVariable Long id) {
        return ResponseEntity.ok(docenteService.obtenerPorId(id));
    }

    @GetMapping("/especialidad/{especialidad}")
    @Operation(summary = "Buscar docentes por especialidad")
    public ResponseEntity<List<DocenteResponse>> buscarPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(docenteService.buscarPorEspecialidad(especialidad));
    }

    @GetMapping("/apellidos/{apellidos}")
    @Operation(summary = "Buscar docentes por apellidos")
    public ResponseEntity<List<DocenteResponse>> buscarPorApellidos(@PathVariable String apellidos) {
        return ResponseEntity.ok(docenteService.buscarPorApellidos(apellidos));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo docente")
    public ResponseEntity<DocenteResponse> registrarDocente(@Valid @RequestBody DocenteRequest request) {
        DocenteResponse nuevo = docenteService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un docente")
    public ResponseEntity<DocenteResponse> actualizarDocente(
            @PathVariable Long id,
            @Valid @RequestBody DocenteRequest request) {
        return ResponseEntity.ok(docenteService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un docente")
    public ResponseEntity<Void> eliminarDocente(@PathVariable Long id) {
        docenteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}