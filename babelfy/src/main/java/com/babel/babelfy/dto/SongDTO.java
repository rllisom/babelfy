package com.babel.babelfy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private long id;
    private String name;
    private int duration;
    private List<Long> artistDTOList;
    private String album;
    private LocalDate date;
    private long id_category;

}

