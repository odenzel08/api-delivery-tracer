package com.project.deliverytracer.dto;

import com.project.deliverytracer.model.enums.StatusEntrega;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EntregaRequestDTO(
        @NotNull(message = "Campo obrigatório")
        UUID idCliente,
        @NotNull(message = "Campo obrigatório")
        UUID idMotorista,
        @NotNull(message = "Campo obrigatório")
        StatusEntrega status,
        String observacoes
) {
}
