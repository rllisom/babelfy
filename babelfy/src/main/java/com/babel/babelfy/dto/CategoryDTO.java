package com.babel.babelfy.dto;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryDTO  {

    private long id;
    private String name;
    private List <SongDTO> songs;




}
