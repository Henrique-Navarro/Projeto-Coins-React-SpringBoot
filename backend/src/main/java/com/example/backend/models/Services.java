package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "services")
@Table(name = "services")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "custo")
    private float custo;

    @Column(name = "id_projeto")
    private Long id_projeto;

    public Services(String name, String descricao, float custo, Long id_projeto) {
        this.name = name;
        this.descricao = descricao;
        this.custo = custo;
        this.id_projeto = id_projeto;
    }
}
