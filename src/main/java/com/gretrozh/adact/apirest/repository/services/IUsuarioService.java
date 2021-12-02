package com.gretrozh.adact.apirest.repository.services;

import com.gretrozh.adact.apirest.entities.Usuario;
import com.gretrozh.adact.apirest.model.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioDTO save(UsuarioDTO usuarioDTO);
    List<UsuarioDTO> findAll();
    void delete(int id);
    UsuarioDTO findById(int id);
    UsuarioDTO update(UsuarioDTO usuarioDTO, int id);

}
