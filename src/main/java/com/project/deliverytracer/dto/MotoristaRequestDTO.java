package com.project.deliverytracer.dto;

import com.project.deliverytracer.model.enums.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record MotoristaRequestDTO(

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 3, max = 150, message = "Tamanho de campo não permitido")
        String nome,
        @NotBlank(message = "Campo obrigatório")
        @Size(max = 11)
        @CPF(message = "CPF inválido")
        String cpf,
        @NotNull(message = "Campo obrigatório")
        @Past(message = "Data de nascimento inválida")
        LocalDate dataNascimento,
        @NotNull(message = "Campo obrigatório")
        Veiculo veiculo) {
}
