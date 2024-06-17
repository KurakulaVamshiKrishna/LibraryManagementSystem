package org.vamshi.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vamshi.librarymanagementsystem.model.Books;

import java.util.List;
import java.util.UUID;

@Repository
public interface BooksRepo extends JpaRepository<Books, UUID> {

    @Query(value = "select  * from books where genre_id=:id",nativeQuery = true)
    List<Books> findByGenresID(UUID id);

    @Query(value = "select * from books where author_id=:AuthorId", nativeQuery = true)
    List<Books> findByAuthorID(UUID AuthorId);
}
