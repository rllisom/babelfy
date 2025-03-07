package com.babel.babelfy.controller;


import com.babel.babelfy.dto.ArtistDTO;
import com.babel.babelfy.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/artists")
@RequiredArgsConstructor
@RestController
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("")
    public List<ArtistDTO> getAll (){
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    public ArtistDTO getById(@PathVariable long id){
        return artistService.getByID(id);
    }

    @DeleteMapping("/{id}")
    public ArtistDTO delete(@PathVariable long id){
        return artistService.delete(id);
    }

    @PutMapping("/{id}")
    public ArtistDTO put(@RequestBody ArtistDTO dto){
        return artistService.put(dto);
    }

    @PostMapping("")
    public ArtistDTO post(@RequestBody ArtistDTO dto){
        return artistService.post(dto);
    }

}
