package com.project.deliverytracer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes")
@Data
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "telefone", nullable = false, unique = true, length = 15)
    private String telefone;
    @Column(name = "endereco", nullable = false, length = 150)
    private String endereco;
    @OneToMany(mappedBy = "clientes", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Entregas> entregasCliente;

}
