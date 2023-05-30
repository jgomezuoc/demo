package com.qindel.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BRANDS")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
