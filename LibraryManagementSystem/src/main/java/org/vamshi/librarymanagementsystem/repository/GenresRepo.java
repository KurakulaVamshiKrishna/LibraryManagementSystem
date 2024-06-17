package org.vamshi.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vamshi.librarymanagementsystem.model.Genres;

import java.util.UUID;

public interface GenresRepo extends JpaRepository<Genres, UUID> {
    @Query(value = "select * from genres where genre_name=:name",nativeQuery = true)
    Genres findByGenreName(String name);
}
