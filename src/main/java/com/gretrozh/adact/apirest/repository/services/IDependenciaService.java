package com.gretrozh.adact.apirest.repository.services;

import com.gretrozh.adact.apirest.entities.Dependencia;
import com.gretrozh.adact.apirest.model.DependenciaDTO;

import java.util.List;

public interface IDependenciaService {
    List<DependenciaDTO> findAll();
    DependenciaDTO findById(int id);
    DependenciaDTO save(DependenciaDTO dependenciaDTO);
    DependenciaDTO update(DependenciaDTO dependenciaDTO, int id);
    void delete(int id);
}
