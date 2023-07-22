package com.example.cwauction2.controler;

import com.example.cwauction2.DTO.BidDTO;
import com.example.cwauction2.DTO.BidderNameDTO;
import com.example.cwauction2.DTO.LotDTO;
import com.example.cwauction2.DTO.LotFullInfo;
import com.example.cwauction2.model.Bid;
import com.example.cwauction2.model.Lot;
import com.example.cwauction2.service.AuctionService;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("auction")
public class AuctionControler {
    private final AuctionService auctionService;
    public AuctionControler(AuctionService auctionService) {
        this.auctionService = auctionService;
    }
    @PostMapping("/create")
    public void createLot(@RequestBody LotDTO lot) {
        auctionService.createLot(lot.fromDTO());
    }
    @PostMapping("/start/{id}")
    public void startBidding(@PathVariable int id) {
        auctionService.startBidding(id);
    }
    @PostMapping("/stop/{id}")
    public void stopBidding(@PathVariable int id) {
        auctionService.stopBidding(id);
    }
    @PostMapping("/bid/{id}")
    public void makeBid(@RequestBody BidderNameDTO bid,
                        @PathVariable int id) {
        auctionService.makeBid(bid.fromDTO(), id);
    }
    @GetMapping("/first/{id}")
    public Optional<BidDTO> getFirstBid(@PathVariable int id) {
        return auctionService.getFirtsBid(id);
    }
    @GetMapping("/frequent/{id}")
    public Optional<BidDTO> getMostFrequent(@PathVariable int id) {
        return auctionService.getMostFrequent(id);
    }
    @GetMapping("/fullInfo/{id}")
    public Optional<LotFullInfo> getLotFullInfoById(@PathVariable int id) {
        return auctionService.getLotFullInfoById(id);
    }
    @GetMapping("/lot")
    public List<Lot> getLotsInPageFormat(@RequestParam(required = false, defaultValue = "0") int page, int status) {
        return auctionService.getLotsInPageFormat(page, status);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getLotsInCSV() throws IOException {
        String name = "lots.csv";
        List<Lot> list = auctionService.getLotsInCSV();

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(name));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("id", "title", "status", "description", "startPrice", "bidPrice"));
        list.forEach(lot -> {
            try {
                csvPrinter.printRecord(lot.getId(),lot.getTitle(),lot.getStatus(),lot.getDescription(),lot.getStartPrice(),lot.getBidPrice());
                csvPrinter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Resource resource = new PathResource(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}