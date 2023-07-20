package com.example.cwauction2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.cwauction2.DTO.BidderDTO;
import com.example.cwauction2.model.Bid;


import java.util.Optional;

@Repository
public interface BidderRepository extends JpaRepository<Bid, Integer> {

    @Query("SELECT new com.example.cwauction2.DTO.BidderDTO(b.bidderName,b.bidDate) FROM Bid b WHERE b.lot.id=:id AND b.id= (SELECT b2.id FROM Bid b2 WHERE b2.lot.id = b.lot.id ORDER BY b2.bidDate ASC LIMIT 1)")
    Optional<BidderDTO> findFirstBidder(@Param("id") int id);

    @Query("SELECT new com.example.cwauction2.DTO.BidderDTO(b.bidderName,b.bidDate) FROM Bid b WHERE b.lot.id=:id AND  b.id= (SELECT b2.id FROM Bid b2 WHERE b2.lot.id = b.lot.id ORDER BY b2.bidDate desc LIMIT 1)")
    Optional<BidderDTO> findLastBidder(@Param("id") int id);

    @Query("SELECT new com.example.cwauction2.DTO.BidderDTO(b.bidderName,min(b.bidDate)) FROM Bid b WHERE b.lot.id=:id GROUP BY b.bidderName ORDER BY count(*) desc LIMIT 1")
    Optional<BidderDTO> findMaxBid(@Param("id") int id);
}
