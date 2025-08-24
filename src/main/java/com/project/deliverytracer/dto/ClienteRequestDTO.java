package com.project.deliverytracer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 3, max = 150, message = "Tamanho de campo não permitido")
        String nome,
        @NotNull(message = "Campo obrigatório")
        @Size(max = 100, message = "Tamanho de campo não permitido")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 11, max = 15, message = "Tamanho de campo não permitido")
        String telefone,
        @NotBlank(message = "Campo obrigatório")
        @Size(max = 150, message = "Tamanho de campo não permitido")
        String endereco) {
}
