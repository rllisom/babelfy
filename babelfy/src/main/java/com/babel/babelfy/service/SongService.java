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

    //Get by id
    public SongDTO getById (long id){
        Song s;
        SongDTO dto;
        s = songRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encuentra la canción"));
        dto=   buildSongDTO(s);

        return dto;

    }

    //Add song
    public SongDTO add(SongDTO dto){
        Song s;
        String name;
        name= dto.getName().trim();
        List <Song> list = songRepository.findAll();

        for (Song song: list){
            if(song.getName().trim().equalsIgnoreCase(name)){
                return null;
            }
        }

        s= convertToSong(dto);
        songRepository.save(s);
        return dto;
    }


    //Build

    public SongDTO buildSongDTO (Song song){
        return  SongDTO.builder()
                .date(song.getDate())
                .id_category(song.getCategory() != null ? song.getCategory().getId() : null)
                .album(song.getAlbum())
                .duration(song.getDuration())
                .artist(song.getArtist())
                .name(song.getName())
                .id_category(song.getCategory().getId())
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
