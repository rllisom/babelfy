package com.babel.babelfy.repository;

import com.babel.babelfy.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
