package com.project.deliverytracer.controller;

import com.project.deliverytracer.dto.ClienteRequestDTO;
import com.project.deliverytracer.dto.ClienteResponseDTO;
import com.project.deliverytracer.dto.EntregaResponseDTO;
import com.project.deliverytracer.mapper.ClienteMapper;
import com.project.deliverytracer.model.Clientes;
import com.project.deliverytracer.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClientesController implements HeaderLocation {

    private final ClienteService service;
    private final ClienteMapper mapper;


    @PostMapping
    public ResponseEntity<Void> salvarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {

        Clientes clientes = mapper.toEntity(clienteRequestDTO);
        service.salvar(clientes);

        var location = gerarHeaderLocation(clientes.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}/minhas-entregas")
    public ResponseEntity<List<EntregaResponseDTO>> entregasClientes(@PathVariable("id") String id) {
        var entregasCliente = service.entregasDoCliente(id);
        return ResponseEntity.ok(entregasCliente);
    }

    @GetMapping("{id}")
    public ResponseEntity<Clientes> detalhesCliente(@PathVariable("id") String id) {
        var clientes = service.buscarCliente(id);
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @PathVariable("id") String id, @RequestBody @Valid ClienteRequestDTO dto) {
        var cliente = service.buscarCliente(id);
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setEndereco(dto.endereco());
        service.salvar(cliente);
        return ResponseEntity.ok(mapper.toDTO(cliente));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") String id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
