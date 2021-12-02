package com.gretrozh.adact.apirest.repository.services;


import com.gretrozh.adact.apirest.entities.Participante;
import com.gretrozh.adact.apirest.model.ParticipanteDTO;

import javax.servlet.http.Part;
import java.util.List;

public interface IParticipanteService {
    ParticipanteDTO save(Participante participante);
    void delete(int id);
    List<ParticipanteDTO> findAll();
    ParticipanteDTO findById(int id);
    ParticipanteDTO update(Participante participante, int id);
}
