package com.babel.babelfy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSongDTO {

    private long id;
    private String name;
    private int duration;
    private String artist;
    private String album;
    private String categoryName;


}
