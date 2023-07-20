package com.example.cwauction2.DTO;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LotFullInfoDTO {
    private Integer id;
    private String status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private Integer currentPrice;
    private BidderDTO lastBid;

    public LotFullInfoDTO(Integer id,
                          String status,
                          String title,
                          String description,
                          Integer startPrice,
                          Integer bidPrice,
                          Integer currentPrice,
                          BidderDTO lastBid) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.bidPrice = bidPrice;
        this.currentPrice = currentPrice;
        this.lastBid = lastBid;
    }

    public LotFullInfoDTO() {
    }
}