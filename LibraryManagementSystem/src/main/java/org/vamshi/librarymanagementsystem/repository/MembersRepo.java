package org.vamshi.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vamshi.librarymanagementsystem.model.Members;

import java.util.UUID;

@Repository
public interface MembersRepo extends JpaRepository<Members, UUID> {

    @Query(value = "select * from members where name=:name",nativeQuery = true)
    Members finfByName(String name);
}
