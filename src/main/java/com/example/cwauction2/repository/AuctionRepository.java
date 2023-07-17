package com.example.cwauction2.repository;

import com.example.cwauction2.model.Lot;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Lot,Integer> {


    @Query(value = "select * from lot " +
            "where status = :status",
            nativeQuery = true)
    List<Lot> findAllByStatus(PageRequest of, int status);
}