package com.gretrozh.adact.apirest.repository.serviceImpl;

import com.gretrozh.adact.apirest.entities.Dependencia;
import com.gretrozh.adact.apirest.exception.GenericException;
import com.gretrozh.adact.apirest.model.DependenciaDTO;
import com.gretrozh.adact.apirest.model.UsuarioDTO;
import com.gretrozh.adact.apirest.repository.dao.IDependenciaDao;
import com.gretrozh.adact.apirest.repository.services.IDependenciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependenciaServiceImpl implements IDependenciaService {

    @Autowired
    private IDependenciaDao dependenciaDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DependenciaDTO> findAll() {

        return dependenciaDao.findAll()
                .stream()
                .map(dependencia-> modelMapper.map(dependencia, DependenciaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DependenciaDTO findById(int id) {
        Dependencia dependencia = dependenciaDao.findById(id).orElse(null);
        if(dependencia == null ){
            throw new GenericException(HttpStatus.NOT_FOUND,"La Dependencia No existe en la BD");
        }
        return dependencia;
    }

    @Override
    public DependenciaDTO save(DependenciaDTO dependenciaDTO) {
        if(dependencia.getNombre()==null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"La dependencia no se puede registrar sin nombre valido");
        }
        return dependenciaDao.save(dependencia);
    }

    @Override
    public DependenciaDTO update(DependenciaDTO dependenciaDTO, int id) {
        Dependencia dependenciaEncontrada= dependenciaDao.findById(id).orElse(null);
        if(dependenciaEncontrada==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"Ese id no esta asignado a ninguna dependencia");
        }
        if(dependencia.getNombre()==null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El nombre de la dependencia debe ser valido para poder actualizarlo");
        }
        dependenciaEncontrada.setNombre(dependencia.getNombre());
        dependenciaDao.save(dependenciaEncontrada);
        return dependenciaEncontrada;
    }

    @Override
    public void delete(int id) {
        Dependencia dependencia = dependenciaDao.findById(id).orElse(null);
        if(dependencia==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"La dependencia no existe en la BD");
        }
        dependenciaDao.deleteById(id);
    }

}
