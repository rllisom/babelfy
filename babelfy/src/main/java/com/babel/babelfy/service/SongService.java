package com.babel.babelfy.service;


import com.babel.babelfy.dto.ArtistDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Artist;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.repository.ArtistRepository;
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
    private final ArtistRepository artistRepository;

    //BUILDER

    public SongDTO songToSongDTO(Song s){

        List<Long> artistId = new ArrayList<>();

        for(Artist a : s.getListArtist()){
            artistId.add(a.getId());
        }

        return new SongDTO(s.getId(),
                s.getName(),
                s.getDuration(),
                artistId,
                s.getAlbum(),
                s.getDate(),
                categoryRepository.findById(s.getCategory().getId()).orElseThrow(()
                        -> new RuntimeException("Categoría no encontrada")).getId());
    }

    public SongDTO buildSongDTO (Song song){
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

    public ArtistDTO converToArtistDTO (Artist a){

        List<SongDTO> songDTOList = new ArrayList<SongDTO>();

        for (Song song : a.getSongList()){
            songDTOList.add(buildSongDTO(song));
        }

        return ArtistDTO.builder()
                .id(a.getId())
                .name(a.getName())
                .songDTOList(songDTOList)
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
        return buildSongDTO(s);
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

        List <Song> list = songRepository.findAll   ();
        boolean repetido = false;

        for (Song song: list){

            for (Artist a : song.getListArtist()) {
                for (Song s2 : a.getSongList()) {
                    if (s2.getName().equalsIgnoreCase(song.getName())) {
                        repetido = true;
                    }
                }
            }

            if(song.getName().trim().equalsIgnoreCase(name)
                    && repetido
                    && song.getDuration() == dto.getDuration()
                    && song.getAlbum().equalsIgnoreCase(dto.getAlbum().trim())
                    && song.getDate().equals(dto.getDate())
                    && song.getCategory().getId() == dto.getId_category()){
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

       if(s.getName().equalsIgnoreCase(song.getName())
               && songRepository.findById(s.getId_artist()).orElseThrow(()
               -> new RuntimeException("Artista no encontrado")).getName().equalsIgnoreCase(song.getArtist().getName())
               && s.getDuration() == song.getDuration() && s.getAlbum().equalsIgnoreCase(song.getAlbum()) && s.getDate().equals(song.getDate())
               && s.getId_category() == song.getCategory().getId()){
           return null;
        }else {
           song.setName(s.getName());
           song.setAlbum(s.getAlbum());
           song.setArtist(songRepository.findById(s.getId_artist()).orElseThrow(()
                   -> new RuntimeException("Artista no encontrado")).getArtist());
           song.setDuration(s.getDuration());
           song.setDate(s.getDate());
           song.setCategory(categoryRepository.findById(s.getId_category()).orElseThrow(()
                   -> new RuntimeException("Categoría no encontrada")));
           songRepository.save(song);
       }
        return s;
    }







}
