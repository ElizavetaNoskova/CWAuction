package com.example.cwauction2.DTO;

import com.example.cwauction2.model.Lot;
import com.example.cwauction2.model.Status;

public class LotDTO {

    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;

    public LotDTO() {
    }

    public static LotDTO fromLot(Lot lot) {

        LotDTO lotDTO = new LotDTO();

        lotDTO.setTitle(lot.getTitle());
        lotDTO.setDescription(lot.getDescription());
        lotDTO.setStartPrice(lot.getStartPrice());
        lotDTO.setBidPrice(lot.getBidPrice());

        return lotDTO;
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
}