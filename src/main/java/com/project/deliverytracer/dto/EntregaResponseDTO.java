package com.project.deliverytracer.dto;

import com.project.deliverytracer.model.enums.StatusEntrega;

import java.util.UUID;

public record EntregaResponseDTO(
        UUID codigoRastreamento, ClienteResponseDTO cliente,
        MotoristaResponseDTO motorista, StatusEntrega status, String observacoes) {
}
