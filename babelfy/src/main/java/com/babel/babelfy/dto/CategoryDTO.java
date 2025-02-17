package com.babel.babelfy.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany
    private List<SongDTO> listDTO = new ArrayList<SongDTO>();

    public CategoryDTO(String name, List<SongDTO> listDTO) {
        this.name = name;
        this.listDTO = listDTO;
    }
}
