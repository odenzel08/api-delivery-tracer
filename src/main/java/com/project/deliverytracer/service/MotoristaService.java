package com.project.deliverytracer.service;

import com.project.deliverytracer.dto.EntregaResponseDTO;
import com.project.deliverytracer.exceptions.CadastroNaoEncontradoException;
import com.project.deliverytracer.exceptions.IdadeNaoPermitidaException;
import com.project.deliverytracer.mapper.EntregaMapper;
import com.project.deliverytracer.model.Motoristas;
import com.project.deliverytracer.repository.EntregaRepository;
import com.project.deliverytracer.repository.MotoristaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository repository;
    private final EntregaRepository entregaRepository;
    private final EntregaMapper mapper;

        public void salvar(Motoristas motoristas) {
            LocalDate hoje = LocalDate.now();
            Period idade = Period.between(motoristas.getDataNascimento(), hoje);
            if (idade.getYears() < 18){
                throw new IdadeNaoPermitidaException("Idade não permitida para cadastrar motorista");
            } else {
                repository.save(motoristas);
            }
        }

        public List<EntregaResponseDTO> entregasDoMotorista (String id){
            var lista = entregaRepository.findByMotoristaId(UUID.fromString(id));
            if (lista.isEmpty()) {
                return null;
            }
            var entregasMotorista = lista
                    .stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());
            return entregasMotorista;
        }

        public Motoristas infoMotorista(String id){
            return repository.findById(UUID.fromString(id)).orElseThrow(
                    () -> new CadastroNaoEncontradoException("Motorista não encontrado, verifique se o ID é válido")
            );
        }

        public void deletar(String id){
            var motorista = repository.findById(UUID.fromString(id)).orElseThrow(
                    () -> new CadastroNaoEncontradoException("Motorista não encontrado, verifique se o ID é válido"));
            if (motorista.getEntregasMotorista().isEmpty()){
                repository.delete(motorista);
            } else {
                throw new RuntimeException("Não é possível excluir o motorista, ele possui entregas cadastradas.");
            }
        }
}
