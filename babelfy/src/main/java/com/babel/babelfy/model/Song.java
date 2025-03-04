package com.babel.babelfy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table (name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int duration;

    @ManyToMany
    private List<Artist> listArtist;

    private String album;
    private LocalDate date;

    @ManyToOne
    private Category category;

    public Song(String name, int duration, List<Artist> listArtist, String album, LocalDate date, Category category) {
        this.name = name;
        this.duration = duration;
        this.listArtist = listArtist;
        this.album = album;
        this.date = date;
        this.category = category;
    }
}
