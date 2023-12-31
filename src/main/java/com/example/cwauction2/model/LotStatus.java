package com.example.cwauction2.model;
import lombok.Getter;

@Getter
public enum LotStatus {
    STARTED("STARTED"),
    STOPPED("STOPPED"),
    CREATED("CREATED");
    private String status;
    LotStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
    }

