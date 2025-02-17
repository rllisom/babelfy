package com.babel.babelfy.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor


public class SongDTO {

    private long id;
    private String name;
    private int duration;
    private String artist;
    private String album;
    private LocalDate date;
    private long id_category;


}
