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



//    //ADD
//    public CreateCategoryResponse create(CreateCategoryRequest newCat){
//
//        Category category = new Category(newCat.getName());
//
//        Category categorySaved = categoryRepository.save(category);
//
//        CreateCategoryResponse categoryResponse = new CreateCategoryResponse(
//                categorySaved.getId(),
//                categorySaved.getName(),
//                categorySaved.getSongs().isEmpty() && categorySaved.getSongs().size() > 0
//                        ? categorySaved.getSongs().stream().map(song ->SongDTO.SongToSongDTO(song)).toList()
//                        : null);
//
//        return categoryResponse;
//    }


    //BUILDER


//    public CreateCategoryResponse buildResponse (Category c){
//        return CreateCategoryResponse.builder()
//                .name(c.getName())
//                .build()
//                ;
//    }

    public CategoryDTO buildDTO(Category c) {
        List<SongDTO> songs = c.getSongs() != null ?
                c.getSongs().stream().map(this::convertToSongDTO).collect(Collectors.toList())
                : new ArrayList<>();
        return CategoryDTO.builder()
                .id(c.getId())
                .songs(songs)
                .name(c.getName())
                .build();

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



}
