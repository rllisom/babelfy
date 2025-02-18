package com.babel.babelfy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
