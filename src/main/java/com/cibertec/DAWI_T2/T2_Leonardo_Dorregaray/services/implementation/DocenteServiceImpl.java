package com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.services.implementation;

import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.entities.Docente;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.exceptions.ResourceNotFoundException;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.models.DocenteRequest;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.models.DocenteResponse;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.repositories.DocenteRepository;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.services.DocenteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;

    public DocenteServiceImpl(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    @Override
    public List<DocenteResponse> listarTodos() {
        return docenteRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public DocenteResponse obtenerPorId(Long id) {
        Docente docente = buscarPorIdOLanzarExcepcion(id);
        return convertirAResponse(docente);
    }

    @Override
    public List<DocenteResponse> buscarPorEspecialidad(String especialidad) {
        return docenteRepository.findByEspecialidadIgnoreCase(especialidad)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public List<DocenteResponse> buscarPorApellidos(String apellidos) {
        return docenteRepository.findByApellidosContainingIgnoreCase(apellidos)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public DocenteResponse registrar(DocenteRequest request) {
        Docente docente = Docente.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .especialidad(request.getEspecialidad())
                .fechaIngreso(request.getFechaIngreso())
                .build();

        Docente guardado = docenteRepository.save(docente);
        return convertirAResponse(guardado);
    }

    @Override
    public DocenteResponse actualizar(Long id, DocenteRequest request) {
        Docente docente = buscarPorIdOLanzarExcepcion(id);

        docente.setNombres(request.getNombres());
        docente.setApellidos(request.getApellidos());
        docente.setCorreo(request.getCorreo());
        docente.setTelefono(request.getTelefono());
        docente.setEspecialidad(request.getEspecialidad());
        docente.setFechaIngreso(request.getFechaIngreso());

        Docente actualizado = docenteRepository.save(docente);
        return convertirAResponse(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        Docente docente = buscarPorIdOLanzarExcepcion(id);
        docenteRepository.delete(docente);
    }

    private Docente buscarPorIdOLanzarExcepcion(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con id: " + id));
    }

    private DocenteResponse convertirAResponse(Docente docente) {
        return DocenteResponse.builder()
                .id(docente.getId())
                .nombres(docente.getNombres())
                .apellidos(docente.getApellidos())
                .correo(docente.getCorreo())
                .telefono(docente.getTelefono())
                .especialidad(docente.getEspecialidad())
                .fechaIngreso(docente.getFechaIngreso())
                .build();
    }
}