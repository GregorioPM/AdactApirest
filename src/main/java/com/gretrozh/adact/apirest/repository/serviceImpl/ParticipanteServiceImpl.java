package com.gretrozh.adact.apirest.repository.serviceImpl;

import com.gretrozh.adact.apirest.entities.Acta;
import com.gretrozh.adact.apirest.entities.Participante;
import com.gretrozh.adact.apirest.entities.Usuario;
import com.gretrozh.adact.apirest.exception.GenericException;
import com.gretrozh.adact.apirest.model.ParticipanteDTO;
import com.gretrozh.adact.apirest.repository.dao.IActaDao;
import com.gretrozh.adact.apirest.repository.dao.IParticipanteDao;
import com.gretrozh.adact.apirest.repository.dao.IUsuarioDao;
import com.gretrozh.adact.apirest.repository.services.IParticipanteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipanteServiceImpl implements IParticipanteService {

    @Autowired
    private IParticipanteDao participanteDao;

    @Autowired
    private IActaDao actaDao;

    @Autowired
    private IUsuarioDao usuarioDao;

    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public ParticipanteDTO save(Participante participante) {
        Participante participanteFound = participanteDao.findParticipante(participante.getUsuario().getId(),participante.getActa().getId());
        Acta acta= actaDao.findById(participante.getActa().getId()).orElse(null);
        Usuario usuario = usuarioDao.findById(participante.getUsuario().getId()).orElse(null);
        if(acta==null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El acta no existe en la BD");
        }
        if(usuario==null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El usuario no existe en la BD");
        }
        if(participanteFound!=null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El usuario ya es participante en esta acta");
        }

        participanteDao.save(participante);
        ParticipanteDTO participanteDTO = modelMapper.map(participante,ParticipanteDTO.class);
        return participanteDTO;
    }

    @Override
    public void delete(int id) {
        Participante participante = participanteDao.findById(id).orElse(null);
        if(participante==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"No se encuentra el participante");
        }
        participanteDao.deleteById(id);
    }

    @Override
    public List<ParticipanteDTO> findAll() {
        List<Participante> participantes = participanteDao.findAll();
        return findParticipantes(participantes);
    }

    private List<ParticipanteDTO> findParticipantes(List<Participante> participantes){
        ParticipanteDTO participanteDTO=null;
        List<ParticipanteDTO> participantesDTO = new ArrayList<ParticipanteDTO>();
        if(!participantes.isEmpty()){
            for (Participante p : participantes){
                participanteDTO= modelMapper.map(p,ParticipanteDTO.class);
                participantesDTO.add(participanteDTO);
            }
        }
        return  participantesDTO;
    }

    @Override
    public ParticipanteDTO findById(int id) {
        ParticipanteDTO participanteDTO=null;
        Participante participante = participanteDao.findById(id).orElse(null);
        if(participante==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"No se encuentra el participante");
        }
        participanteDTO=modelMapper.map(participante,ParticipanteDTO.class);
        return participanteDTO;
    }

    @Override
    public ParticipanteDTO update(Participante participante, int id) {
        ParticipanteDTO participanteDTO=null;
        Participante participanteFound = participanteDao.findById(id).orElse(null);
        if(participanteFound==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"No se encuentra el participante en la BD");
        }
        if(participante.getEstado()==null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El estado no debe estar vacio");
        }
        participanteFound.setEstado(participante.getEstado());
        participanteDao.save(participanteFound);
        participanteDTO=modelMapper.map(participanteFound,ParticipanteDTO.class);
        return participanteDTO;
    }
}
