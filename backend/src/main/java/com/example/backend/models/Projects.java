package com.example.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity(name = "projects")
@Table(name = "projects")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "orcamento")
    private float orcamento;

    @Column(name = "custo")
    private float custo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category categoria;

    public Projects(String name, float orcamento, float custo, Category categoria) {
        this.name = name;
        this.custo = custo;
        this.orcamento = orcamento;
        this.categoria = categoria;
    }
}
