package org.vamshi.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vamshi.librarymanagementsystem.model.Publishers;

import java.util.UUID;

public interface PublishersRepo extends JpaRepository<Publishers, UUID> {

    @Query(value = "select * from publishers where publisher_name=:name",nativeQuery = true)
    Publishers findByPublisherName(String name);
}
