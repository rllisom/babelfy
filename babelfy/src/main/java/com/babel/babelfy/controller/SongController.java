package com.babel.babelfy.controller;

import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.model.Song;
import com.babel.babelfy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/songs")
@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;


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
