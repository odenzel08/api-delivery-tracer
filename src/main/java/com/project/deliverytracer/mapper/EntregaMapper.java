package com.project.deliverytracer.mapper;


import com.project.deliverytracer.dto.EntregaRequestDTO;
import com.project.deliverytracer.dto.EntregaResponseDTO;
import com.project.deliverytracer.model.Entregas;
import com.project.deliverytracer.repository.ClienteRepository;
import com.project.deliverytracer.repository.MotoristaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, MotoristaMapper.class})
public abstract class EntregaMapper {

    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected MotoristaRepository motoristaRepository;

    @Mapping(target = "clientes", expression = "java(clienteRepository.findById(dto.idCliente()).orElseThrow(" +
            "() -> new jakarta.persistence.EntityNotFoundException(\"Cliente não encontrado\")))")
    @Mapping(target = "motoristas", expression = "java(motoristaRepository.findById(dto.idMotorista()).orElseThrow(" +
            "() -> new jakarta.persistence.EntityNotFoundException(\"Motorista não encontrado\")))")
    public abstract Entregas toEntity(EntregaRequestDTO dto);

    @Mapping(target = "cliente", source = "clientes")
    @Mapping(target = "motorista", source = "motoristas")
    public abstract EntregaResponseDTO toDTO(Entregas entregas);
}
