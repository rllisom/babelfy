package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Builder
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    //BUILDER

    public Category buildCategory (CategoryDTO c){
        List<Song> list = null;

        if(c.getSongsDTO() != null && !c.getSongsDTO().isEmpty()) {
            list = new ArrayList<Song>();
            for (SongDTO s : c.getSongsDTO()) {
                list.add(new Song(s.getId(),
                        s.getName(),
                        s.getDuration(),
                        s.getArtist(),
                        s.getAlbum(),
                        s.getDate(),
                        categoryRepository.findById(s.getId_category()).orElse(null)));
            }
        }
        return new Category( c.getId(),c.getName(),list);
    }

    public CategoryDTO buildCategoryDTO (Category c){
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
    public CategoryDTO add(CategoryDTO dto){
        Category c = buildCategory(dto);
        categoryRepository.save(c);
        return dto;

    }



}
