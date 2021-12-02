package com.gretrozh.adact.apirest.controllers;

import com.gretrozh.adact.apirest.entities.Acta;
import com.gretrozh.adact.apirest.repository.services.IActaService;
import com.gretrozh.adact.apirest.repository.services.IDependenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
@RequestMapping("/acta")
@Secured("ROLE_ADMIN")
public class ActaController {

    @Autowired
    private IActaService actaService;

    @GetMapping("")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(actaService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardar(@RequestBody Acta acta){
        return ResponseEntity.status(HttpStatus.CREATED).body(actaService.save(acta));
    }
}
