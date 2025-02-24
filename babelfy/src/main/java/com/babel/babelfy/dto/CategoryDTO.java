package com.babel.babelfy.dto;

import com.babel.babelfy.model.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {


    private long id;
    private String name;
    private List<SongDTO> songsDTO ;

    public CategoryDTO(String name, List<SongDTO> songsDTO) {
        this.name = name;
        this.songsDTO = songsDTO;
    }


}
