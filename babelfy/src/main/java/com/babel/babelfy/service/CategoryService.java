package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Artist;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.ArtistRepository;
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
    private final ArtistRepository artistRepository;
    //BUILDER

    public Category buildCategory (CategoryDTO c){
        List<Song> list = null;

        if(c.getSongsDTO() != null && !c.getSongsDTO().isEmpty()) {
            list = new ArrayList<Song>();
            for (SongDTO s : c.getSongsDTO()) {
                list.add(convertToSong(s));
            }
        }
        return new Category( c.getId(),c.getName(),list);
    }

    public CategoryDTO buildCategoryDTO (Category c){
        List<SongDTO> list = null;

        if(c.getSongs() != null && !c.getSongs().isEmpty()) {
            list = new ArrayList<SongDTO>();
            for (Song s : c.getSongs()) {
                list.add(convertToSongDTO(s));
            }
        }
        return new CategoryDTO( c.getId(),c.getName(),list);
    }

    public SongDTO convertToSongDTO(Song song) {
        List<Long> artistDTOList = new ArrayList<Long>();
        for (Artist artist : song.getListArtist()) {
            artistDTOList.add(artist.getId());
        }

        return  SongDTO.builder()
                .date(song.getDate())
                .id(song.getId())
                .album(song.getAlbum())
                .duration(song.getDuration())
                .artistDTOList(artistDTOList)
                .name(song.getName())
                .id_category(song.getCategory().getId())
                .build();

    }


    private Song convertToSong(SongDTO songDTO) {

        System.out.println("ID de categoría recibido: " + songDTO.getId_category());

        List<Artist> artistList = new ArrayList<Artist>();

        for (long s : songDTO.getArtistDTOList()){
            artistList.add(artistRepository.findById(s).orElseThrow(()
                    -> new RuntimeException("Artista no encontrado")));
        }


        return Song.builder()
                .name(songDTO.getName())
                .date(songDTO.getDate())
                .album(songDTO.getAlbum())
                .listArtist(artistList)
                .duration(songDTO.getDuration())
                .id(songDTO.getId())
                .category(categoryRepository.findById(songDTO.getId_category()).orElseThrow(()
                        ->new RuntimeException("Categoría" + " no encontrada")))

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
