package com.project.deliverytracer.dto;

import com.project.deliverytracer.model.enums.Veiculo;

public record MotoristaResponseDTO(
        String nome, Veiculo veiculo) {
}
