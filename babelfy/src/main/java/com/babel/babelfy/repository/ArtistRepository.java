package com.babel.babelfy.repository;

import com.babel.babelfy.model.Artist;
import com.babel.babelfy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
