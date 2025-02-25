package com.babel.babelfy.controller;

import com.babel.babelfy.dto.SongDTO;
import com.babel.babelfy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/songs")
@RequiredArgsConstructor
@RestController
public class SongController {

    private final SongService songService;

    //GET ALL
    @GetMapping("")
    public List<SongDTO> getAll (){
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


    //PUT
    @PutMapping("/{id}")
    public SongDTO put( @PathVariable long id, @RequestBody SongDTO s){
        return songService.put(id,s);
    }




}
