package com.gretrozh.adact.apirest.repository.services;


import com.gretrozh.adact.apirest.entities.Acta;

import java.util.List;

public interface IActaService {
    Acta save(Acta acta);
    void delete(int id);
    List<Acta> findAll();
    Acta findById(int id);
    Acta update(Acta acta, int id);
}
