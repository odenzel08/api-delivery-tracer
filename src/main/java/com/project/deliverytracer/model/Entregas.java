package com.project.deliverytracer.model;

import com.project.deliverytracer.model.enums.StatusEntrega;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "entregas")
@Data
public class Entregas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_rastreamento")
    private UUID codigoRastreamento;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Clientes clientes;
    @ManyToOne
    @JoinColumn(name = "id_motorista")
    private Motoristas motoristas;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private StatusEntrega status;
    @Column(name = "observacoes")
    private String observacoes;

}
