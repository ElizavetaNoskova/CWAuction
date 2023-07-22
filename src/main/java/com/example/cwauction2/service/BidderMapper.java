package com.example.cwauction2.service;

import com.example.cwauction2.DTO.BidderDTO;
import com.example.cwauction2.model.Bid;
import org.springframework.stereotype.Component;

@Component
public class BidderMapper {
    public BidderDTO toDTO(Bid bid){
        BidderDTO bidderDTO = new BidderDTO();
        bidderDTO.setBidderName(bid.getBidderName());
        bidderDTO.setBidDate(bid.getBidDate());

        return bidderDTO;
    }
    public Bid toEntity(BidderDTO bidderDTO){
        Bid bid = new Bid();
        bid.setBidderName(bidderDTO.getBidderName());
        bid.setBidDate(bid.getBidDate());
        return bid;
    }
}