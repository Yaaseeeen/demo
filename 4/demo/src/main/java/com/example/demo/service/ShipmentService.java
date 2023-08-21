package com.example.demo.service;

import com.example.demo.entity.PostOffice;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.ShipmentHistory;
import com.example.demo.entity.ShipmentStatus;
import com.example.demo.repository.ShipmentHistoryRepository;
import com.example.demo.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentHistoryRepository shipmentHistoryRepository;
    private final PostOfficeService postOfficeService;

    @Transactional
    public Shipment registerShipment(Shipment shipment) {
        shipment.setStatus(ShipmentStatus.REGISTERED);
        shipmentRepository.save(shipment);
        ShipmentHistory history = new ShipmentHistory();
        history.setShipment(shipment);
        history.setDate(LocalDateTime.now());
        history.setLocation("Sender's address");
        history.setComment("Registered by sender.");
        shipmentHistoryRepository.save(history);
        return shipment;
    }

    public List<Shipment> getAllShipment() {
        return shipmentRepository.findAll();
    }

    public Shipment findById(Long shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new NoSuchElementException("Shipment with id " + shipmentId + " not found"));
    }

    @Transactional
    public Shipment arrivedAtIntermediateOffice(Long postOfficeId, Long shipmentId) {
        Shipment shipment = findById(shipmentId);
        shipment.setStatus(ShipmentStatus.ARRIVED_AT_INTERMEDIATE_OFFICE);
        PostOffice postOffice = postOfficeService.findById(postOfficeId);
        shipmentRepository.save(shipment);
        ShipmentHistory history = new ShipmentHistory();
        history.setShipment(shipment);
        history.setDate(LocalDateTime.now());
        history.setLocation(postOffice.getAddress());
        history.setComment("Arrived at intermediate office.");
        shipmentHistoryRepository.save(history);
        return shipment;
    }

    @Transactional
    public Shipment departedFromOffice(Long postOfficeId, Long shipmentId) {
        Shipment shipment = findById(shipmentId);
        PostOffice postOffice = postOfficeService.findById(postOfficeId);
        shipmentRepository.save(shipment);
        ShipmentHistory history = new ShipmentHistory();
        history.setShipment(shipment);
        history.setDate(LocalDateTime.now());
        history.setLocation(postOffice.getAddress());
        history.setComment("Departed from office.");
        shipmentHistoryRepository.save(history);
        return shipment;
    }

    @Transactional
    public Shipment receivedByRecipient(Long shipmentId) {
        Shipment shipment = findById(shipmentId);
        shipment.setStatus(ShipmentStatus.RECEIVED_BY_RECIPIENT);
        shipmentRepository.save(shipment);
        ShipmentHistory history = new ShipmentHistory();
        history.setShipment(shipment);
        history.setDate(LocalDateTime.now());
        history.setLocation(shipment.getRecipientAddress());
        history.setComment("Received by recipient.");
        shipmentHistoryRepository.save(history);
        return shipment;
    }

    @Transactional(readOnly = true)
    public List<ShipmentHistory> getShipmentHistory(Long shipmentId) {
        return shipmentHistoryRepository.findAllByShipmentId(shipmentId);
    }

}
