package com.gretrozh.adact.apirest.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dependencia")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dependencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dependencia")
    private int id;
    private String nombre;

    @JsonManagedReference
    @OneToMany(mappedBy = "dependencia")
    private List<Acta> actas;
}
