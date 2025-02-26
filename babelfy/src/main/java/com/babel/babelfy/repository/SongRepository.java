package com.babel.babelfy.repository;

import com.babel.babelfy.model.Song;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    @Transactional
    List<Song> findByName(@Param("name") String name);
}
