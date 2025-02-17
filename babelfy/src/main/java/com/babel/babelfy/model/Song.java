package com.babel.babelfy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int duration;
    private String artist,album;
    private LocalDate date;

    @ManyToOne
    private Category category;

    public Song(String name, int duration, String artist, String album, LocalDate date, Category category) {
        this.name = name;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.date = date;
        this.category = category;
    }
}
