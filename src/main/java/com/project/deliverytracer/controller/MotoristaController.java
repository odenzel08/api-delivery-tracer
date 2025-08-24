package com.project.deliverytracer.controller;

import com.project.deliverytracer.dto.EntregaResponseDTO;
import com.project.deliverytracer.dto.MotoristaRequestDTO;
import com.project.deliverytracer.dto.MotoristaResponseDTO;
import com.project.deliverytracer.mapper.MotoristaMapper;
import com.project.deliverytracer.model.Motoristas;
import com.project.deliverytracer.service.MotoristaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("motoristas")
@RequiredArgsConstructor
public class MotoristaController implements HeaderLocation{

    private final MotoristaService service;
    private final MotoristaMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarMotorista (@RequestBody @Valid MotoristaRequestDTO dto){
        Motoristas motorista = mapper.toEntity(dto);
        service.salvar(motorista);

        var location = gerarHeaderLocation(motorista.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}/minhas-entregas")
    public ResponseEntity<List<EntregaResponseDTO>> entregasDoMotorista(@PathVariable("id") String id) {
        var entregasDoMotorista = service.entregasDoMotorista(id);
        return ResponseEntity.ok(entregasDoMotorista);
    }

    @GetMapping("{id}")
    public ResponseEntity<Motoristas> detalhesMotorista(@PathVariable ("id") String id){
        var motorista = service.infoMotorista(id);
        return ResponseEntity.ok(motorista);
    }

    @PutMapping("{id}")
    public ResponseEntity<MotoristaResponseDTO> atualizarMotorista(@PathVariable("id") String id, @RequestBody @Valid MotoristaRequestDTO dto){
        Motoristas motorista = service.infoMotorista(id);
        motorista.setCpf(dto.cpf());
        motorista.setNome(dto.nome());
        motorista.setDataNascimento(dto.dataNascimento());
        motorista.setVeiculo(dto.veiculo());
        service.salvar(motorista);
        return ResponseEntity.ok(mapper.toDTO(motorista));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarMotorista(@PathVariable("id") String id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
