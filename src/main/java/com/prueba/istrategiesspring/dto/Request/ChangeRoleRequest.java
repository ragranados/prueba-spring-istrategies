package com.prueba.istrategiesspring.dto.Request;

public class ChangeRoleRequest {
    private Long idUsuario;
    private Long nuevoRolId;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getNuevoRolId() {
        return nuevoRolId;
    }

    public void setNuevoRolId(Long nuevoRolId) {
        this.nuevoRolId = nuevoRolId;
    }
}
