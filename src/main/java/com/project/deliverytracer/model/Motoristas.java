package com.project.deliverytracer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.deliverytracer.model.enums.Veiculo;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "motoristas")
@Data
public class Motoristas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    @Column(name = "veiculo", length = 30, nullable = false)
    private Veiculo veiculo;
    @OneToMany(mappedBy = "motoristas", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Entregas> entregasMotorista;
}
