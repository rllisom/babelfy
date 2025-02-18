package com.babel.babelfy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany()
    private List<Song> songs;

    public Category(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }
}
