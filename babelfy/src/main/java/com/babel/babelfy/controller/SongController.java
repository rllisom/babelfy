package com.babel.babelfy.controller;

import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.babel.babelfy.dto.ResponseSongDTO;
import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.SongService;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/songs")
@RequiredArgsConstructor
@RestController
public class SongController {

    private final SongService songService;

    //GET ALL
    @GetMapping("")
    public List<ResponseSongDTO> getAll (){
        return songService.getAll();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public SongDTO delete(@PathVariable long id){
        return songService.delete(id);
    }

    //GET BY ID
    @GetMapping ("/{id}")
    public SongDTO getById(@PathVariable long id){
        return  songService.getById(id);
    }

    //POST
    @PostMapping("")
    public SongDTO add (@RequestBody SongDTO dto){
        return songService.add(dto);
    }






}
