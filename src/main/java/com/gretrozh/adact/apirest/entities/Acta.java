package com.gretrozh.adact.apirest.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "acta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Acta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acta")
    private int id;
    private String asunto;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private String horaInicio;
    private String horaFinal;
    private String lugar;
    private String ordenDelDia;
    private String conclusiones;
    private String elaboro;
    private String aprobo;
    private String estado;

    //bi-directional many-to-one association to Dependencia
    @ManyToOne
    @JoinColumn(name = "id_dependencia")
    @JsonBackReference
    private Dependencia dependencia;

    @OneToMany(mappedBy = "acta")
    @JsonManagedReference
    private List<Participante> participantes;


}
