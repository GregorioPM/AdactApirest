package com.gretrozh.adact.apirest.controllers;

import com.gretrozh.adact.apirest.entities.Dependencia;
import com.gretrozh.adact.apirest.repository.services.IDependenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
@RequestMapping("/dependencia")
@Secured("ROLE_ADMIN")
public class DependenciaController {

    @Autowired
    private IDependenciaService dependenciaService;

    @GetMapping("")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(dependenciaService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> buscarPorId(@PathVariable int id){
        return  ResponseEntity.ok(dependenciaService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?>guardar(@RequestBody Dependencia dependencia){
        return ResponseEntity.status(HttpStatus.CREATED).body(dependenciaService.save(dependencia));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@PathVariable int id, @RequestBody Dependencia dependencia){
        return ResponseEntity.ok(dependenciaService.update(dependencia,id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable int id){
        dependenciaService.delete(id);
        return  ResponseEntity.ok().build();
    }

}
