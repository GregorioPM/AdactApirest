package com.gretrozh.adact.apirest.repository.serviceImpl;

import com.gretrozh.adact.apirest.entities.Usuario;
import com.gretrozh.adact.apirest.exception.GenericException;
import com.gretrozh.adact.apirest.model.UsuarioDTO;
import com.gretrozh.adact.apirest.repository.dao.IUsuarioDao;
import com.gretrozh.adact.apirest.repository.services.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {

        Usuario usuarioFound= userDao.findByEmail(usuarioDTO.getEmail());
        if(usuarioFound!=null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El email ya existe en un usuario registrado");
        }
        String bcryptPassword= passwordEncoder.encode(usuarioDTO.getPassword());
        usuarioDTO.setPassword(bcryptPassword);
        Usuario usuario = modelMapper.map(usuarioDTO,Usuario.class);
        userDao.save(usuario);
        usuarioDTO = modelMapper.map(usuario,UsuarioDTO.class);
        return usuarioDTO;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return userDao.findAll().stream()
                .map(usuario -> modelMapper.map(usuario,UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        Usuario usuario= userDao.findById(id).orElse(null);
        if(usuario==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"El usuario no existe en la BD");
        }
        userDao.deleteById(id);
    }

    @Override
    public UsuarioDTO findById(int id) {
        Usuario usuario= userDao.findById(id).orElse(null);
        if(usuario==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"El usuario no existe en la BD");
        }
        UsuarioDTO usuarioDTO = modelMapper.map(usuario,UsuarioDTO.class);
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDTO, int id) {
        Usuario usuario = userDao.findById(id).orElse(null);
        Usuario usuarioFound= userDao.findByEmail(usuarioDTO.getEmail());
        if(usuario==null){
            throw new GenericException(HttpStatus.NOT_FOUND,"El usuario no existe en la BD");
        }
        if(!usuario.getEmail().equals(usuarioDTO.getEmail()) && usuarioFound!=null){
            throw new GenericException(HttpStatus.BAD_REQUEST,"El email ya existe en un usuario registrado");
        }
        String bcryptPassword= passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(bcryptPassword);
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setCargo(usuarioDTO.getCargo());
        userDao.save(usuario);
        usuarioDTO = modelMapper.map(usuario,UsuarioDTO.class);
        return usuarioDTO;
    }
}
