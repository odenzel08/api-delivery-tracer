package com.project.deliverytracer.controller;

import com.project.deliverytracer.dto.EntregaRequestDTO;
import com.project.deliverytracer.dto.EntregaResponseDTO;
import com.project.deliverytracer.mapper.EntregaMapper;
import com.project.deliverytracer.model.Entregas;
import com.project.deliverytracer.repository.ClienteRepository;
import com.project.deliverytracer.repository.MotoristaRepository;
import com.project.deliverytracer.service.ClienteService;
import com.project.deliverytracer.service.EntregaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("entregas")
@RequiredArgsConstructor
public class EntregaController implements HeaderLocation{

    private final EntregaService service;
    private final EntregaMapper mapper;
    private final ClienteRepository clienteRepository;
    private final MotoristaRepository motoristaRepository;

    @PostMapping
    public ResponseEntity<EntregaResponseDTO> criarEntrega(@RequestBody @Valid EntregaRequestDTO dto){

        Entregas entrega = mapper.toEntity(dto);
        service.salvar(entrega);
        return ResponseEntity.ok(mapper.toDTO(entrega));
    }

    @PutMapping("{codigoRastreamento}")
    public ResponseEntity<EntregaResponseDTO> atualizarEntrega(@PathVariable ("codigoRastreamento") String codigo,
                                                               @RequestBody @Valid EntregaRequestDTO dto){
        var entrega = service.buscarEntrega(codigo);
        var cliente = clienteRepository.buscarPorId(dto.idCliente());
        var motorista = motoristaRepository.buscarPorId(dto.idMotorista());

        entrega.setClientes(cliente);
        entrega.setMotoristas(motorista);
        entrega.setStatus(dto.status());
        entrega.setObservacoes(dto.observacoes());
        service.salvar(entrega);
        return ResponseEntity.ok(mapper.toDTO(entrega));
    }

    @DeleteMapping("{codigoRastreamento}")
    public ResponseEntity<Void> deletarEntrega(@PathVariable ("codigoRastreamento") String codigo){
        service.deletar(codigo);
        return ResponseEntity.noContent().build();
    }

}
