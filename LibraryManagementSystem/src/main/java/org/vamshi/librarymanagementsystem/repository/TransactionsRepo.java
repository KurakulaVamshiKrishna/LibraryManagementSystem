package org.vamshi.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vamshi.librarymanagementsystem.model.Transactions;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, UUID> {

    @Query(value = "select due_date from transactions where referenceid=:refID",nativeQuery = true)
    Timestamp findByReferenceID(UUID refID);

    @Query(value = "select * from transactions where referenceid=:refID",nativeQuery = true)
    List<Transactions> findAllByReferenceID(UUID refID);

    @Query(value = "select * from transactions where member_id=:memberID",nativeQuery = true)
    List<Transactions> findBooksByMemberID(UUID memberID);
}
