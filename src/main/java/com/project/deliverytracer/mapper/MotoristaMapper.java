package com.project.deliverytracer.mapper;

import com.project.deliverytracer.dto.MotoristaRequestDTO;
import com.project.deliverytracer.dto.MotoristaResponseDTO;
import com.project.deliverytracer.model.Motoristas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MotoristaMapper {

    Motoristas toEntity(MotoristaRequestDTO dto);

    MotoristaResponseDTO toDTO(Motoristas motoristas);
}
