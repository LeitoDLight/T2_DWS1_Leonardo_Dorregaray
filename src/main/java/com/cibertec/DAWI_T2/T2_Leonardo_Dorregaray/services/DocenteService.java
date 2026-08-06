package com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.services;

import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.models.DocenteRequest;
import com.cibertec.DAWI_T2.T2_Leonardo_Dorregaray.models.DocenteResponse;

import java.util.List;

public interface DocenteService {

    List<DocenteResponse> listarTodos();

    DocenteResponse obtenerPorId(Long id);

    List<DocenteResponse> buscarPorEspecialidad(String especialidad);

    List<DocenteResponse> buscarPorApellidos(String apellidos);

    DocenteResponse registrar(DocenteRequest request);

    DocenteResponse actualizar(Long id, DocenteRequest request);

    void eliminar(Long id);
}