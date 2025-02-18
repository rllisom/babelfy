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


    //BUILDER
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

    public Category buildCategory(CategoryDTO dto) {
        List<Song> songs = dto.getSongs() != null ?
                dto.getSongs().stream().map(this::convertToSong).collect(Collectors.toList())
                : new ArrayList<>();

        return Category.builder()
                .songs(songs)
                .name(dto.getName())
                .build();
    }
    public SongDTO convertToSongDTO(Song song) {
        return  SongDTO.builder()
                .date(song.getDate())
                .id_category(song.getCategory() != null ? song.getCategory().getId() : null)
                .album(song.getAlbum())
                .duration(song.getDuration())
                .artist(song.getArtist())
                .build();


    }


    private Song convertToSong(SongDTO songDTO) {
        return Song.builder()
                .name(songDTO.getName())
                .date(songDTO.getDate())
                .album(songDTO.getAlbum())
                .artist(songDTO.getArtist())
                .duration(songDTO.getDuration())

                .build();
    }




     //ALL
    @Transactional
    public List<CategoryDTO> getListCategory() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryDTO> dto = list.stream().map(this::buildDTO).collect(Collectors.toList());

        return dto;
    }
     //DELETE
     @Transactional
    public CategoryDTO deleteCategory(long id) {
        Category c;
        c = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese id "));
        categoryRepository.delete(c);
        return buildDTO(c);
    }

    //PUT
    @Transactional
    public CategoryDTO put(long id, String name){
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        c.setName(name);
        categoryRepository.save(c);
        return buildDTO(c);
    }

    
    //GET BY ID
    @Transactional
    public CategoryDTO getById(long id){
        Category c = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se encuentra la categoría") );

        return buildDTO(c);

    }
}
