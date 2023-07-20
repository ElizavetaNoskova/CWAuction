package com.example.cwauction2.service;

import com.example.cwauction2.DTO.BidderDTO;
import com.example.cwauction2.DTO.LotCSVDTO;
import com.example.cwauction2.DTO.LotFullInfoDTO;
import com.example.cwauction2.model.Bid;
import com.example.cwauction2.model.Lot;
import com.example.cwauction2.model.LotStatus;
import com.example.cwauction2.repository.BidderRepository;
import com.example.cwauction2.service.BidderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BidderServiceImpl implements BidderService {
    private final BidderRepository bidderRepository;
    private final BidderMapper bidderMapper;
    private final LotService lotService;

    Logger logger = LoggerFactory.getLogger(BidderServiceImpl.class);

    public BidderServiceImpl(BidderRepository bidderRepository, BidderMapper bidderMapper, LotService lotService) {
        this.bidderRepository = bidderRepository;
        this.bidderMapper = bidderMapper;
        this.lotService = lotService;
    }

    @Override
    public List<BidderDTO> addNewBid(List<BidderDTO> bidderDTOS, int id) {
        logger.debug("Создание ставки на лот id=" + id);
        LocalDateTime localDateTime = LocalDateTime.now();
        Lot lot = lotService.getLotById(id);
        if (lot.getStatus().equals(LotStatus.CREATED.getStatus()) || lot.getStatus().equals(LotStatus.STOPPED.getStatus())) {
            throw new RuntimeException();
        }
        Optional<BidderDTO> bidders = bidderDTOS.stream()
                .filter(bidderDTO -> bidderDTO.getBidderName().isEmpty())
                .findFirst();
        if (bidders.isPresent()) {
            throw new RuntimeException();
        }
        return bidderRepository.saveAll(
                        bidderDTOS.stream()
                                .map(bidderMapper::toEntity)
                                .peek(bid -> {
                                            bid.setLot(lot);
                                            bid.setBidDate(localDateTime.toString());
                                        }
                                )
                                .collect(Collectors.toList())
                )
                .stream()
                .map(bidderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BidderDTO findFirstBidder(int id) {
        return bidderRepository.findFirstBidder(id).orElseThrow(()->{
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }

    @Override
    public BidderDTO lastBid(int id) {
        return bidderRepository.findLastBidder(id).orElseThrow(()->{
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }

    @Override
    public BidderDTO findMaxBid(int id) {
        return bidderRepository.findMaxBid(id).orElseThrow(() -> {
            logger.error("ставка не найдена. id =" + id);
            return new RuntimeException();
        });
    }

    @Override
    public void createLot(Lot lot) {

    }

    @Override
    public void startBidding(int id) {

    }

    @Override
    public void stopBidding(int id) {

    }

    @Override
    public void makeBid(Bid bid, int id) {

    }

    @Override
    public Optional<LotCSVDTO> getFirstBid(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<LotCSVDTO> getMostFrequent(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<LotFullInfoDTO> getLotFullInfoById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Lot> getLotsInPageFormat(int page, int status) {
        return null;
    }

    @Override
    public List<Lot> getLotsInCSV() {
        return null;
    }
}