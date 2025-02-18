package com.babel.babelfy.service;

import com.babel.babelfy.repository.*;

@Service
public interface SongService {

    @Autowired
    private SongRepository repository;

    public Song add(Song s) {
        return repository.save(s)
    }
}
