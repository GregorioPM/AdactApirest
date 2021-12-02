package com.gretrozh.adact.apirest.controllers;

import com.gretrozh.adact.apirest.entities.Usuario;
import com.gretrozh.adact.apirest.model.UsuarioDTO;
import com.gretrozh.adact.apirest.repository.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findId(@PathVariable int id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardar(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.update(usuarioDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
