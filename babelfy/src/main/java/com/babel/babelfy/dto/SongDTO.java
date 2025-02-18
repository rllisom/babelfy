package com.babel.babelfy.dto;

import com.babel.babelfy.model.Song;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class SongDTO {


    private String name;
    private int duration;
    private String artist;
    private String album;
    private LocalDate date;
    private long id_category;

//    public static SongDTO SongToSongDTO(Song song){
//        return SongDTO.builder()
//                .id(song.getId())
//                .album(song.getAlbum())
//                .artist(song.getArtist())
//                .duration(song.getDuration())
//                .name(song.getName())
//                .date(song.getDate())
//                .id_category(song.getCategory().getId())
//                .build();
//    }

}
