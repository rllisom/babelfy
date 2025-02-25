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

    public SongDTO buildSongDTO (Song song){
        return  SongDTO.builder()
                .date(song.getDate())
                .id(song.getId())
                .album(song.getAlbum())
                .duration(song.getDuration())
                .artist(song.getArtist())
                .name(song.getName())
                .id_category(song.getCategory().getId())
                .build();

    }
    private Song convertToSong(SongDTO songDTO) {

        System.out.println("ID de categoría recibido: " + songDTO.getId_category());

        return Song.builder()
                .name(songDTO.getName())
                .date(songDTO.getDate())
                .album(songDTO.getAlbum())
                .artist(songDTO.getArtist())
                .duration(songDTO.getDuration())
                .id(songDTO.getId())
                .category(categoryRepository.findById(songDTO.getId_category()).orElseThrow(()
                        ->new RuntimeException("Categoría" + " no encontrada")))

                .build();



    }

    //GET ALL
    @Transactional
    public List<SongDTO> getAll (){
        List<Song> list = songRepository.findAll();
        List<SongDTO> listDTO = new ArrayList<SongDTO>();

        for (Song s : list){
            listDTO.add(songToSongDTO(s));
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

    //GET BY ID
    public SongDTO getById (long id){
        Song s;
        SongDTO dto;
        s = songRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encuentra la canción"));
        dto=   buildSongDTO(s);

        return dto;

    }

    //POST
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


    //PUT

    @Transactional
    public SongDTO put(long id, SongDTO s){

        System.out.println(s);

        Song song = songRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Canción no encontrada"));

        song.setName(s.getName());
        song.setAlbum(s.getAlbum());
        song.setArtist(s.getArtist());
        song.setDuration(s.getDuration());
        song.setDate(s.getDate());
        song.setCategory(categoryRepository.findById(s.getId_category()).orElseThrow(()
                -> new RuntimeException("Categoría no encontrada")));
        songRepository.save(song);

        return s;
    }





}
