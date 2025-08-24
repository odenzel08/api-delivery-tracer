package com.project.deliverytracer.repository;

import com.project.deliverytracer.model.Entregas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EntregaRepository extends JpaRepository<Entregas, UUID> {

    @Query("SELECT e FROM Entregas e WHERE e.clientes.id = :clienteId")
    List<Entregas> findByClienteId(@Param("clienteId") UUID clienteId);

    @Query("SELECT e FROM Entregas e WHERE e.motoristas.id = :motoristaId")
    List<Entregas> findByMotoristaId(@Param("motoristaId") UUID motoristaId);
}
