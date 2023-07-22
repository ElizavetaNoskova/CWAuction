package com.example.cwauction2.service;

import com.example.cwauction2.DTO.LotDTO;
import com.example.cwauction2.DTO.LotFullInfoDTO;
import com.example.cwauction2.model.Lot;

import java.io.IOException;
import java.util.List;

public interface LotService {
    List<LotDTO> addNewLot(List<LotDTO>  lotDTO);
    void startBiddingForLotId(int id);
    void stopBiddingForLotId(int id);
    Lot getLotById(int id);
    LotFullInfoDTO getFullInfo(int id);
    void csvFile() throws IOException;
    String readTextFromFile(String fileName);
}