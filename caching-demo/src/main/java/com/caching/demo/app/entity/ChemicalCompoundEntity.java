package com.caching.demo.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChemicalCompoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chemical_name")
    private String ccName;
    @Column(name = "chemical_formula")
    private String ccFormula;

}
