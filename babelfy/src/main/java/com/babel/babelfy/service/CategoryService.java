package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repo;

    //ALL
    public List <CategoryDTO> getListCategory_Ser(){
          List<Category> list = repo.findAll();
          List<CategoryDTO> dto = new ArrayList <CategoryDTO>();

          for(Category c : list){
              dto.add(buildDTO(c));
          }
          return dto;
    }

    //DELETE
    public CategoryDTO deleteCategory(long id) {
        Category c;
        c = repo.findById(id).orElseThrow(() -> new RuntimeException("No existe ese id "));
        repo.delete(c);
        return buildDTO(c);
    }

    //ADD
    public Category addNewCategory(CategoryDTO newCat){
        repo.save(buildCategory(newCat));
        return buildCategory(newCat);
    }



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
    public Category buildCategory (CategoryDTO c){
        List<Song> songs = null;

        if(c.getSongs() != null && !c.getSongs().isEmpty()) {
            songs = new ArrayList<Song>();
            for (SongDTO s : c.getSongs()) {
                songs.add(new Song(s.getId(),
                        s.getName(),
                        s.getDuration(),
                        s.getArtist(),
                        s.getAlbum(),
                        s.getDate(),
                        repo.findById(s.getId_category()).orElse(null)));
            }
        }
        return new Category(c.getId(),c.getName(),songs);
    }

}
