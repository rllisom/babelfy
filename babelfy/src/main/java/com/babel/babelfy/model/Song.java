package com.babel.babelfy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
public class Song {



    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int duration;
    private String artist;
    private String album;
    private LocalDate date;
    @ManyToOne
    private Category category;
}
