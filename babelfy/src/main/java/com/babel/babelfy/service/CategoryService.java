package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import com.babel.babelfy.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SongRepository songRepository;

    //BUILDER

    public Category buildCategory (CategoryDTO c){
        List<Song> list = null;

        if(c.getSongsDTO() != null && !c.getSongsDTO().isEmpty()) {
            list = new ArrayList<Song>();
            for (SongDTO s : c.getSongsDTO()) {
                list.add(new Song(s.getId(),
                        s.getName(),
                        s.getDuration(),
                        songRepository.findById(s.getId_artist()).orElseThrow(()
                                -> new RuntimeException("No se ha encontrado el artista")).getArtist(),
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
                        s.getArtist().getId(),
                        s.getAlbum(),
                        s.getDate(),
                        c.getId()));
            }
        }
        return new CategoryDTO( c.getId(),c.getName(),list);
    }

    public SongDTO convertToSongDTO(Song song) {
        return  SongDTO.builder()
                .date(song.getDate())
                .id_category(song.getCategory() != null ? song.getCategory().getId() : null)
                .album(song.getAlbum())
                .duration(song.getDuration())
                .id_artist(song.getArtist().getId())
                .build();


    }


    private Song convertToSong(SongDTO songDTO) {
        return Song.builder()
                .name(songDTO.getName())
                .date(songDTO.getDate())
                .album(songDTO.getAlbum())
                .artist(songRepository.findById(songDTO.getId_artist()).orElseThrow(()
                        -> new RuntimeException("No se ha encontrado el artista")).getArtist())
                .duration(songDTO.getDuration())

                .build();
    }




     //ALL
    @Transactional
    public List<CategoryDTO> getListCategory() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryDTO> dto = list.stream().map(this::buildCategoryDTO).collect(Collectors.toList());

        return dto;
    }
    //Find by Name
    public long findByName(String name){
        boolean encontrado = false;
        long id = 0;
        List<Category> categoryList= categoryRepository.findAll();
        for (int i = 0; i < categoryList.size() && !encontrado; i++) {
            if(categoryList.get(i).getName().equalsIgnoreCase(name)){
                encontrado = true;
                id = categoryList.get(i).getId();
            }
        }
        return id;
    }
     //DELETE
     @Transactional
    public CategoryDTO deleteCategory(long id, String name) {
        List<Song> list = songRepository.findAll() ;
        Category c;
        Category undef = categoryRepository.findById(findByName(name)).orElseThrow(()
        -> new RuntimeException("Categoría no encontrada"));

        c = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese id "));

        for (Song s : list){
             if(s.getCategory().getId() == c.getId()){
                 s.setCategory(undef);
                 songRepository.save(s);
             }
         }
        categoryRepository.delete(c);
        return buildCategoryDTO(c);
    }

    //PUT
    @Transactional
    public CategoryDTO put(long id, String name) {

        name = name.trim();
        List<Category> list = categoryRepository.findAll();
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        for(Category category : list){
            if(category.getName().trim().equalsIgnoreCase(name)) {
                return null;
            }
        }
        if (c.getName().trim().equalsIgnoreCase(name) ) {
            return null;
        }

            c.setName(name);
            categoryRepository.save(c);
            return buildCategoryDTO(c);

    }
    
    //GET BY ID
    @Transactional
    public CategoryDTO getById(long id){
        Category c = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se encuentra la categoría") );

        return buildCategoryDTO(c);

    }

    //POST

    @Transactional
    public CategoryDTO add(CategoryDTO dto){
        List <Category> list = categoryRepository.findAll();

        String name = dto.getName().trim();
        for(Category category : list ){
            if(category.getName().trim().equalsIgnoreCase(name)){
                return null;
            }
        }

        Category c = buildCategory(dto);
        categoryRepository.save(c);
        return dto;

    }



}
