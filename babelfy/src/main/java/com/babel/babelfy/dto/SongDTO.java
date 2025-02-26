package com.babel.babelfy.dto;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@Data
@NoArgsConstructor
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
