package com.babel.babelfy.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "listArtist", fetch = FetchType.EAGER)
    private List<Song> songList;


    public Artist(String name, List<Song> songList) {
        this.name = name;
        this.songList = songList;
    }
}
