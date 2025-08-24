package com.project.deliverytracer.repository;

import com.project.deliverytracer.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Clientes, UUID> {

    @Query("SELECT c FROM Clientes c WHERE c.id = :id")
    Clientes buscarPorId(@Param("id") UUID id);


}
