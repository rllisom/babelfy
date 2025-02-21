package com.babel.babelfy.service;


import com.babel.babelfy.dto.ResponseSongDTO;
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

@RequiredArgsConstructor
@Service
public class SongService {

    private final SongRepository songRepository;
    private final CategoryRepository categoryRepository;

    //BUILDER
    public ResponseSongDTO songToResponseSongDTO(Song s){
        Category category = categoryRepository.findById(s.getCategory().getId()).orElseThrow(()
                -> new RuntimeException("Categoría no encontrada"));
        return new ResponseSongDTO(s.getId(),
                s.getName(),
                s.getDuration(),
                s.getArtist(),
                s.getAlbum(),
                category.getName());

    }

    public SongDTO songToSongDTO(Song s){
        return new SongDTO(s.getId(),
                s.getName(),
                s.getDuration(),
                s.getArtist(),
                s.getAlbum(),
                s.getDate(),
                categoryRepository.findById(s.getCategory().getId()).orElseThrow(()
                        -> new RuntimeException("Categoría no encontrada")).getId());
    }


    //GET ALL
    @Transactional
    public List<ResponseSongDTO> getAll (){
        List<Song> list = songRepository.findAll();
        List<ResponseSongDTO> listDTO = new ArrayList<ResponseSongDTO>();

        for (Song s : list){
            listDTO.add(songToResponseSongDTO(s));
        }

        return listDTO;
    }

    //DELETE
    @Transactional
    public SongDTO delete(long id){
        Song s = songRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Canción no encontrada"));
        songRepository.delete(s);
        return songToSongDTO(s);
    }


}
