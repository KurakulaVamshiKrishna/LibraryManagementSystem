package org.vamshi.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vamshi.librarymanagementsystem.model.Authors;

import java.util.List;
import java.util.UUID;

public interface AuthorsRepo extends JpaRepository<Authors, UUID> {

    @Query(value = "select * from authors where author_name=:Name", nativeQuery = true)
    Authors findByAuthorName(String Name);
}
