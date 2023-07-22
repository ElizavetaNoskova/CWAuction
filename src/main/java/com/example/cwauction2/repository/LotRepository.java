package com.example.cwauction2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cwauction2.model.Lot;

public interface LotRepository extends JpaRepository<Lot, Integer> {

}