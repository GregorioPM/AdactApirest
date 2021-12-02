package com.gretrozh.adact.apirest.repository.dao;

import com.gretrozh.adact.apirest.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IParticipanteDao extends JpaRepository<Participante,Integer> {
    @Query(value = "Select * from participante p Where p.usuario_id=?1 and p.acta_id=?2",nativeQuery = true)
    Participante findParticipante(Integer idUsuario,Integer idActa);
}
