package com.gretrozh.adact.apirest.controllers;

import com.gretrozh.adact.apirest.entities.Participante;
import com.gretrozh.adact.apirest.repository.services.IParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
@RequestMapping("/acta/participante")
public class ParticipanteController {

    @Autowired
    private IParticipanteService participanteService;

    @GetMapping("")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(participanteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return ResponseEntity.ok(participanteService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardar(@RequestBody Participante participante){
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.save(participante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody Participante participante){
        return ResponseEntity.ok(participanteService.update(participante,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        participanteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
