package com.project.deliverytracer.service;

import com.project.deliverytracer.dto.EntregaResponseDTO;
import com.project.deliverytracer.exceptions.CadastroNaoEncontradoException;
import com.project.deliverytracer.mapper.ClienteMapper;
import com.project.deliverytracer.mapper.EntregaMapper;
import com.project.deliverytracer.model.Clientes;
import com.project.deliverytracer.repository.ClienteRepository;
import com.project.deliverytracer.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper clienteMapper;
    private final EntregaRepository entregaRepository;
    private final EntregaMapper entregaMapper;

    public void salvar(Clientes clientes) {
        repository.save(clientes);
    }

    public List<EntregaResponseDTO> entregasDoCliente(String id) {
        var lista = entregaRepository.findByClienteId(UUID.fromString(id));
        return lista
                .stream()
                .map(entregaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Clientes buscarCliente(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow(
                () -> new CadastroNaoEncontradoException("Cliente não encontrado, verifique se o ID é válido"));
    }

    public void deletar(String id) {
        var cliente = repository.findById(UUID.fromString(id)).orElseThrow(
                () -> new CadastroNaoEncontradoException("Cliente não encontrado, verifique se o ID é válido"));
        if (cliente.getEntregasCliente().isEmpty()) {
            repository.delete(cliente);
        } else {
            throw new RuntimeException("Não é possível excluir o cliente, ele possui entregas cadastradas.");
        }
    }

}
