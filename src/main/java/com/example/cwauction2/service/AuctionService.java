package com.example.cwauction2.service;

import com.example.cwauction2.DTO.BidDTO;
import com.example.cwauction2.DTO.LotFullInfo;
import com.example.cwauction2.model.Bid;
import com.example.cwauction2.model.Lot;

import java.util.List;
import java.util.Optional;

public interface AuctionService {
    void createLot(Lot lot);

    void startBidding(int id);

    void stopBidding(int id);

    void makeBid(Bid bid, int id);

    Optional<BidDTO> getFirtsBid(int id);

    Optional<BidDTO> getMostFrequent(int id);

    Optional<LotFullInfo> getLotFullInfoById(int id);

    List<Lot> getLotsInPageFormat(int page, int status);

    List<Lot> getLotsInCSV();
}