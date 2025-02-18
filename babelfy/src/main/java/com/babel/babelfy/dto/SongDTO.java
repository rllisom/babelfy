package com.babel.babelfy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.babel.babelfy.model.Song;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private String name;
    private long id;
    private int duration;
    private String artist;
    private String album;
    private LocalDate date;
    private long id_category;
 


}
