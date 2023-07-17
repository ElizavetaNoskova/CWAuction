package com.example.cwauction2.service;

import com.example.cwauction2.DTO.BidDTO;
import com.example.cwauction2.DTO.LotFullInfo;
import com.example.cwauction2.model.Bid;
import com.example.cwauction2.model.Lot;
import com.example.cwauction2.model.Status;
import com.example.cwauction2.repository.AuctionRepository;
import com.example.cwauction2.repository.BidRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    public AuctionServiceImpl(AuctionRepository auctionRepository, BidRepository bidRepository) {
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public void createLot(Lot lot) {
        auctionRepository.save(lot);
    }

    @Override
    public void startBidding(int id) {
        Lot lot = new Lot();
        lot.setId(auctionRepository.findById(id).get().getId());
        lot.setTitle(auctionRepository.findById(id).get().getTitle());
        lot.setDescription(auctionRepository.findById(id).get().getDescription());
        lot.setStartPrice(auctionRepository.findById(id).get().getStartPrice());
        lot.setBidPrice(auctionRepository.findById(id).get().getBidPrice());
        lot.setStatus(Status.STARTED);
        auctionRepository.save(lot);
    }

    @Override
    public void stopBidding(int id) {
        Lot lot = new Lot();
        lot.setId(auctionRepository.findById(id).get().getId());
        lot.setTitle(auctionRepository.findById(id).get().getTitle());
        lot.setDescription(auctionRepository.findById(id).get().getDescription());
        lot.setStartPrice(auctionRepository.findById(id).get().getStartPrice());
        lot.setBidPrice(auctionRepository.findById(id).get().getBidPrice());
        lot.setStatus(Status.STOPPED);
        auctionRepository.save(lot);
    }

    @Override
    public void makeBid(Bid bid, int id) {
        Lot lot = new Lot();
        Optional<Lot> lotWithStatus = auctionRepository.findById(id);
        if (!lotWithStatus.get().getStatus().equals(Status.STARTED)) {
            throw new RuntimeException("Некорректный статус лота");
        } else {
            lot.setId(id);
            bid.setLot(lot);
            bidRepository.save(bid);
        }
    }

    @Override
    public Optional<BidDTO> getFirtsBid(int id) {
        return Optional.ofNullable(
                BidDTO.fromBid(bidRepository.getFirstBid(id)));
    }

    @Override
    public Optional<BidDTO> getMostFrequent(int id) {
        return Optional.ofNullable(
                BidDTO.fromBid(bidRepository.getMostFrequent(id)));
    }

    @Override
    public Optional<LotFullInfo> getLotFullInfoById(int id) {

        LotFullInfo lotFullInfo = new LotFullInfo();
        Optional<Lot> lot = auctionRepository.findById(id);
        lotFullInfo.setId(lot.get().getId());
        lotFullInfo.setStatus(lot.get().getStatus());
        lotFullInfo.setTitle(lot.get().getTitle());
        lotFullInfo.setDescription(lot.get().getDescription());
        lotFullInfo.setStartPrice(lot.get().getStartPrice());
        lotFullInfo.setBidPrice(lot.get().getBidPrice());
        lotFullInfo.setCurrentPrice(lot.get().getStartPrice()+ (lotFullInfo.getBidPrice() * bidRepository.getNumberOfBids(id)));
        lotFullInfo.setLastBid(BidDTO.fromBid(bidRepository.getLastBid(id)));
        return Optional.of(lotFullInfo);
    }

    @Override
    public List<Lot> getLotsInPageFormat(int page, int status) {
        PageRequest.of(page, 10);
        return auctionRepository.findAllByStatus(PageRequest.of(page, 10),status);
    }

    @Override
    public List<Lot> getLotsInCSV() {
        List<Lot> list = new ArrayList<>();
        list = auctionRepository.findAll();
        return list;
    }
}