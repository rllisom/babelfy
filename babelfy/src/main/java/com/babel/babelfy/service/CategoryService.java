package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
//import com.babel.babelfy.dto.CreateCategoryRequest;
//import com.babel.babelfy.dto.CreateCategoryResponse;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //ALL
    @Transactional
    public List<CategoryDTO> getListCategory() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryDTO> dto = list.stream().map(this::buildDTO).collect(Collectors.toList());

        return dto;
    }

//Listar categorías
//    public List <CreateCategoryResponse> getAll(){
//        List <Category> list = categoryRepository.findAll();
//        List <CreateCategoryResponse> response = list.stream().map(this::buildResponse).collect(Collectors.toList());
//        return  response;
//    }


    public Category getCategoryById(long idCategory) {

        Optional<Category> category = this.categoryRepository.findById(idCategory);

        if (category.isEmpty()) try {
            throw new ClassNotFoundException("La categoría " + idCategory + " no existe");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return category.get();
    }

    //DELETE
    public CategoryDTO deleteCategory(long id) {
        Category c;
        c = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese id "));
        categoryRepository.delete(c);
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
