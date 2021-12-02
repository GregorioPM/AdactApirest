package com.gretrozh.adact.apirest.repository.dao;

import com.gretrozh.adact.apirest.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDao extends JpaRepository<Usuario,Integer> {
    @Query("select u from Usuario u where u.email=?1")
    Usuario findByEmail(String email);
}
