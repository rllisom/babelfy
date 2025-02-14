package com.babel.babelfy.dto;

import com.babel.babelfy.model.Song;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {



    private String name;
    private List<Song> song;


}
