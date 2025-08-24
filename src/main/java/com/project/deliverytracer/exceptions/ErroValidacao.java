package com.project.deliverytracer.exceptions;

import java.util.List;

public record ErroValidacao(int status, String mensagem, List<ErroCampo> erros) {
}
