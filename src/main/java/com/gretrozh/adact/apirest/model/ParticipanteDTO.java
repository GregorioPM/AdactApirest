package com.gretrozh.adact.apirest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipanteDTO {
    private int id;
    private String usuarioId;
    private String actaID;
    private String estado;
}
