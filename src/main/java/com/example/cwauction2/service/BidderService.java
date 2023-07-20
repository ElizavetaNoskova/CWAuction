package com.example.cwauction2.service;

import com.example.cwauction2.DTO.BidderDTO;
import com.example.cwauction2.DTO.LotCSVDTO;
import com.example.cwauction2.DTO.LotFullInfoDTO;
import com.example.cwauction2.model.Bid;
import com.example.cwauction2.model.Lot;

import java.util.List;
import java.util.Optional;

public interface BidderService {
    List<BidderDTO> addNewBid(List<BidderDTO> bidderDTOS, int id);

    BidderDTO findFirstBidder(int id);

    BidderDTO lastBid(int id);

    BidderDTO findMaxBid(int id);

    void createLot(Lot lot);

    void startBidding(int id);

    void stopBidding(int id);

    void makeBid(Bid bid, int id);

    Optional<LotCSVDTO> getFirstBid(int id);

    Optional<LotCSVDTO> getMostFrequent(int id);

    Optional<LotFullInfoDTO> getLotFullInfoById(int id);

    List<Lot> getLotsInPageFormat(int page, int status);

    List<Lot> getLotsInCSV();
}