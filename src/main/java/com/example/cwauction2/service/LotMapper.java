package com.example.cwauction2.service;

import org.springframework.stereotype.Component;
import com.example.cwauction2.DTO.LotCSVDTO;
import com.example.cwauction2.DTO.LotDTO;
import com.example.cwauction2.DTO.LotFullInfoDTO;
import com.example.cwauction2.model.Lot;
import com.example.cwauction2.repository.BidderRepository;

import static com.example.cwauction2.model.LotStatus.CREATED;


@Component
public class LotMapper {
    private final BidderRepository bidderRepository;

    public LotMapper(BidderRepository bidderRepository) {
        this.bidderRepository = bidderRepository;
    }

    public LotDTO toDto(Lot lot){
        LotDTO lotDTO = new LotDTO();
        lotDTO.setTitle(lot.getTitle());
        lotDTO.setDescription(lot.getDescription());
        lotDTO.setStartPrice(lot.getStartPrice());
        lotDTO.setBidPrice(lot.getBidPrice());
        return lotDTO;
    }

    public Lot toEntity(LotDTO lotDTO){
        Lot lot = new Lot();
        lot.setTitle(lotDTO.getTitle());
        lot.setDescription(lotDTO.getDescription());
        lot.setStartPrice(lotDTO.getStartPrice());
        lot.setBidPrice(lotDTO.getBidPrice());
        lot.setStatus(CREATED.getStatus());
        return lot;
    }
    public LotFullInfoDTO toFullInfo(Lot lot){
        LotFullInfoDTO lotFullInfoDTO = new LotFullInfoDTO();
        lotFullInfoDTO.setId(lot.getId());
        lotFullInfoDTO.setTitle(lot.getTitle());
        lotFullInfoDTO.setDescription(lot.getDescription());
        lotFullInfoDTO.setStartPrice(lot.getStartPrice());
        lotFullInfoDTO.setBidPrice(lot.getBidPrice());
        lotFullInfoDTO.setStatus(lot.getStatus());
        lotFullInfoDTO.setCurrentPrice(lot.getBidList().size() * lot.getBidPrice() + lot.getStartPrice());
        lotFullInfoDTO.setLastBid(bidderRepository.findLastBidder(lot.getId()).orElse(null));
        return lotFullInfoDTO;
    }
    public LotCSVDTO lotCSVDTO(Lot lot){
        LotCSVDTO lotCSVDTO = new LotCSVDTO();
        lotCSVDTO.setId(lot.getId());
        lotCSVDTO.setTitle(lot.getTitle());
        lotCSVDTO.setStatus(lot.getStatus());
        lotCSVDTO.setCurrentPrice(lot.getBidList().size() * lot.getBidPrice() + lot.getStartPrice());
        lotCSVDTO.setLastBid(bidderRepository.findLastBidder(lot.getId()).orElse(null));
        return lotCSVDTO;
    }
}