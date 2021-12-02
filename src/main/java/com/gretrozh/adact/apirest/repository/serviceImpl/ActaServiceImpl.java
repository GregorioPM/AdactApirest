package com.gretrozh.adact.apirest.repository.serviceImpl;

import com.gretrozh.adact.apirest.entities.Acta;
import com.gretrozh.adact.apirest.entities.Dependencia;
import com.gretrozh.adact.apirest.exception.GenericException;
import com.gretrozh.adact.apirest.repository.dao.IActaDao;
import com.gretrozh.adact.apirest.repository.dao.IDependenciaDao;
import com.gretrozh.adact.apirest.repository.services.IActaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActaServiceImpl implements IActaService {

    @Autowired
    IActaDao actaDao;

    @Autowired
    IDependenciaDao dependenciaDao;

    @Override
    public Acta save(Acta acta) {
        Dependencia dependencia = dependenciaDao.findById(acta.getDependencia().getId()).orElse(null);
        Acta actaFound=actaDao.findByAsuntoAndFecha(acta.getAsunto(),acta.getFecha());
        if(actaFound!=null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El acta ya existe en la BD");
        }
        if(dependencia==null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"No se encontro dependencia para asignar al acta");
        }
        actaDao.save(acta);
        return acta;
    }

    @Override
    public void delete(int id) {
        Acta acta = actaDao.findById(id).orElse(null);
        if(acta == null ){
            throw new GenericException(HttpStatus.NOT_FOUND,"El acta no existe en la BD");
        }
        actaDao.deleteById(id);
    }

    @Override
    public List<Acta> findAll() {
        return  actaDao.findAll();
    }

    @Override
    public Acta findById(int id) {
        Acta acta = actaDao.findById(id).orElse(null);
        if(acta == null){
            throw new GenericException(HttpStatus.NOT_FOUND,"El acta no existe en la BD");
        }
        return acta;
    }

    @Override
    public Acta update(Acta acta, int id) {
        return null;
    }
}
