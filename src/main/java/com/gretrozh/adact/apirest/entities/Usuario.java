package com.gretrozh.adact.apirest.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int id;
    private String email;
    private String password;
    private String rol;
    private String nombre;
    private String apellidos;
    private String cargo;


    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Participante> participantes;

}
