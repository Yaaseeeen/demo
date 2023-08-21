package com.example.demo.repository;

import com.example.demo.entity.ShipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentHistoryRepository extends JpaRepository<ShipmentHistory, Long> {

    List<ShipmentHistory> findAllByShipmentId(Long shipmentId);
}