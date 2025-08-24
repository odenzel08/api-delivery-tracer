package com.project.deliverytracer.service;

import com.project.deliverytracer.exceptions.CadastroNaoEncontradoException;
import com.project.deliverytracer.model.Entregas;
import com.project.deliverytracer.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository repository;

    public void salvar(Entregas entrega){
        repository.save(entrega);
    }

    public Entregas buscarEntrega(String cdg){
        return repository.findById(UUID.fromString(cdg)).orElseThrow(
                () -> new CadastroNaoEncontradoException("Não foi possível localizar essa entrega, verifique o código de rastreio.")
        );
    }

    public void deletar(String cdg){
        var entrega = repository.findById(UUID.fromString(cdg)).orElseThrow(
                () -> new CadastroNaoEncontradoException("Não foi possível localizar essa entrega, verifique o código de rastreio.")
        );
        repository.delete(entrega);
    }
}
