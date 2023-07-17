package com.example.cwauction2.DTO;

import com.example.cwauction2.model.Bid;
import com.example.cwauction2.model.Lot;
import com.example.cwauction2.model.Status;

public class LotFullInfo {

    private Integer id;
    private Status status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private Integer currentPrice;
    private BidDTO lastBid;

    public LotFullInfo() {
    }

    public static LotFullInfo fromLot(Lot lot) {
        LotFullInfo lotFullInfo = new LotFullInfo();

        lotFullInfo.setId(lot.getId());
        lotFullInfo.setTitle(lot.getTitle());
        lotFullInfo.setStatus(lot.getStatus());
        lotFullInfo.setDescription(lot.getDescription());
        lotFullInfo.setStartPrice(lot.getStartPrice());
        lotFullInfo.setBidPrice(lot.getBidPrice());
        lotFullInfo.setCurrentPrice(0);
        lotFullInfo.setLastBid(BidDTO.fromBid(new Bid()));

        return lotFullInfo;
    }

    public Lot fromDTO() {

        Lot lot = new Lot();

        lot.setTitle(this.getTitle());
        lot.setDescription(this.getDescription());
        lot.setStartPrice(this.getStartPrice());
        lot.setBidPrice(this.getBidPrice());
        lot.setStatus(Status.CREATED);

        return lot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BidDTO getLastBid() {
        return lastBid;
    }

    public void setLastBid(BidDTO lastBid) {
        this.lastBid = lastBid;
    }
}