package com.example.cwauction2.DTO;

import com.example.cwauction2.model.Bid;

import java.time.LocalDateTime;

public class BidDTO {

    private String name;
    private LocalDateTime localDateTime;

    public BidDTO() {
    }

    public static BidDTO fromBid(Bid bid) {
        BidDTO bidDTO = new BidDTO();

        bidDTO.setName(bid.getName());
        bidDTO.setLocalDateTime(bid.getLocalDateTime());

        return bidDTO;
    }

    public Bid fromDTO() {
        Bid bid = new Bid();

        bid.setName(this.getName());
        bid.setLocalDateTime(this.getLocalDateTime());

        return bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}