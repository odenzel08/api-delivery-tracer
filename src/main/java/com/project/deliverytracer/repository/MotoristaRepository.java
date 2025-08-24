package com.project.deliverytracer.repository;

import com.project.deliverytracer.model.Clientes;
import com.project.deliverytracer.model.Motoristas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MotoristaRepository extends JpaRepository<Motoristas, UUID> {
    @Query("select m from Motoristas m where m.id = :id")
    Motoristas buscarPorId(@Param("id") UUID id);
}
