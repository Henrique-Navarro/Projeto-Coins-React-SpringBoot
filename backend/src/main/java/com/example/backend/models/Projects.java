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

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category categoria;

    public Projects(String name, float orcamento, Category categoria) {
        this.name = name;
        this.orcamento = orcamento;
        this.categoria = categoria;
    }
}
