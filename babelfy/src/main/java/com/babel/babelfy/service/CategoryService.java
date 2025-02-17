package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;


    public CategoryDTO buildDTO (Category c){
        List<SongDTO> list = null;

        if(c.getSongs() != null && !c.getSongs().isEmpty()) {
            list = new ArrayList<SongDTO>();
            for (Song s : c.getSongs()) {
                list.add(new SongDTO(s.getId(),
                        s.getName(),
                        s.getDuration(),
                        s.getArtist(),
                        s.getAlbum(),
                        s.getDate(),
                        c.getId()));
            }
        }
        return new CategoryDTO( c.getId(),c.getName(),list);
    }

    @Transactional
    public CategoryDTO put(long id, String name){
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        c.setName(name);
        categoryRepository.save(c);
        return buildDTO(c);
    }


}
