package com.babel.babelfy.service;

import com.babel.babelfy.dto.ArtistDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Artist;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.ArtistRepository;
import com.babel.babelfy.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    //BUILDER
    public ArtistDTO converToArtistDTO (Artist a){

        List<Long>id_songDTO = new ArrayList<>();

        for (Song song : a.getSongList()){
            id_songDTO.add(song.getId());
        }

        return ArtistDTO.builder()
                .id(a.getId())
                .name(a.getName())
                .id_songDTO(id_songDTO)
                .build();
    }

    public Artist convertToArtist (ArtistDTO dto){

        List<Song> songList = new ArrayList<>();
        Song s;
        for (long i : dto.getId_songDTO()){
            s = songRepository.findById(i).orElseThrow(()
                    -> new RuntimeException("Canción no encontrada"));
            songList.add(s);
        }

        return Artist.builder()
                .id(dto.getId())
                .name(dto.getName())
                .songList(songList)
                .build();


    }
    //GET ALL
    @Transactional
    public List<ArtistDTO> getAll(){
        List<Artist> artistList = artistRepository.findAll();
        List<ArtistDTO> artistDTOList = new ArrayList<>();

        for(Artist a : artistList){
            artistDTOList.add(converToArtistDTO(a));
        }
        return artistDTOList;
    }

    //GET BY ID
    @Transactional
    public ArtistDTO getByID(long id){
        Artist artist = artistRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Artista no encontradp"));

        return converToArtistDTO(artist);
    }

    //DELETE
    @Transactional
    public ArtistDTO delete(long id){
        Artist artist = artistRepository.findById(id).orElseThrow( ()
                -> new RuntimeException("Artista no encontrado"));

        for (Song s : artist.getSongList()){
            songRepository.delete(s);
        }
        artistRepository.delete(artist);
        return converToArtistDTO(artist);
    }

    //POST
    @Transactional
    public ArtistDTO post(ArtistDTO artistDTO){
        List<Artist> artistList = artistRepository.findAll();
        boolean find = false;
        for(Artist a : artistList){
            if (a.getName().equalsIgnoreCase(artistDTO.getName())){
                find = true;
            }
        }
        if(find){
            return null;
        }else{
            artistRepository.save(convertToArtist(artistDTO));
            return artistDTO;
        }
    }

    //PUT
    @Transactional
    public ArtistDTO put(ArtistDTO artistDTO){
        boolean find = false;
        Artist artist = artistRepository.findById(artistDTO.getId()).orElseThrow(
                () -> new RuntimeException("Artista no encontrado"));
        List<Artist> artistList = artistRepository.findAll();
        for (Artist a : artistList ){
            if (a.getName().equalsIgnoreCase(artistDTO.getName())){
                find = true;
            }
        }
        if(find) {
            return null;
        }else {
            artist.setName(artistDTO.getName());
            artistRepository.save(artist);
            return artistDTO;
        }
    }
}
