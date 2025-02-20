package com.babel.babelfy.service;

import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SongService {

    private final SongRepository songRepository;


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
