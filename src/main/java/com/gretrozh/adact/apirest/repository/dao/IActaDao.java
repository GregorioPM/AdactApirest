package com.gretrozh.adact.apirest.repository.dao;

import com.gretrozh.adact.apirest.entities.Acta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface IActaDao extends JpaRepository<Acta,Integer> {

    Acta findByAsuntoAndFecha(String asunto, Date fecha);
}
