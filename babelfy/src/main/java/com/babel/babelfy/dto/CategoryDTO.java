package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class CategoryDTO  {

    private long id;
    private String name;
    private List <SongDTO> songs;


}
