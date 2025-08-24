package com.project.deliverytracer.mapper;

import com.project.deliverytracer.dto.ClienteRequestDTO;
import com.project.deliverytracer.dto.ClienteResponseDTO;
import com.project.deliverytracer.model.Clientes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {


    Clientes toEntity(ClienteRequestDTO dto);

    ClienteResponseDTO toDTO(Clientes clientes);
}
