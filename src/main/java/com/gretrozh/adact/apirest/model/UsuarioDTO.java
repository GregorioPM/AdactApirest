package com.gretrozh.adact.apirest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private int id;
    private String email;
    private String password;
    private String rol;
    private String nombre;
    private String apellidos;
    private String cargo;
}
