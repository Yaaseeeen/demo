package com.example.demo.service;

import com.example.demo.entity.PostOffice;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.ShipmentHistory;
import com.example.demo.entity.ShipmentStatus;
import com.example.demo.repository.ShipmentHistoryRepository;
import com.example.demo.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private ShipmentHistoryRepository shipmentHistoryRepository;

    @Mock
    private PostOfficeService postOfficeService;

    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shipmentService = new ShipmentService(shipmentRepository, shipmentHistoryRepository, postOfficeService);
    }

    @Test
    void registerShipment_ShouldReturnRegisteredShipment() {
        
        Shipment shipment = new Shipment();
        shipment.setStatus(ShipmentStatus.REGISTERED);
        when(shipmentRepository.save(shipment)).thenReturn(shipment);

       
        Shipment registeredShipment = shipmentService.registerShipment(shipment);

        
        assertEquals(ShipmentStatus.REGISTERED, registeredShipment.getStatus());
        verify(shipmentRepository, times(1)).save(shipment);
        verify(shipmentHistoryRepository, times(1)).save(any(ShipmentHistory.class));
    }

    @Test
    void getAllShipment_ShouldReturnAllShipments() {
        
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment());
        shipments.add(new Shipment());
        when(shipmentRepository.findAll()).thenReturn(shipments);

       
        List<Shipment> allShipments = shipmentService.getAllShipment();

        
        assertEquals(shipments, allShipments);
        verify(shipmentRepository, times(1)).findAll();
    }

    @Test
    void findById_WithValidId_ShouldReturnShipment() {
        
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(shipment));

       
        Shipment foundShipment = shipmentService.findById(shipmentId);

        
        assertEquals(shipment, foundShipment);
        verify(shipmentRepository, times(1)).findById(shipmentId);
    }

    @Test
    void findById_WithInvalidId_ShouldThrowNoSuchElementException() {
        
        Long shipmentId = 1L;
        when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> shipmentService.findById(shipmentId));
        verify(shipmentRepository, times(1)).findById(shipmentId);
    }

    @Test
    void arrivedAtIntermediateOffice_ShouldReturnUpdatedShipment() {
        
        Long postOfficeId = 1L;
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        shipment.setStatus(ShipmentStatus.ARRIVED_AT_INTERMEDIATE_OFFICE);
        PostOffice postOffice = new PostOffice();
        when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(shipment));
        when(postOfficeService.findById(postOfficeId)).thenReturn(postOffice);
        when(shipmentRepository.save(shipment)).thenReturn(shipment);

       
        Shipment updatedShipment = shipmentService.arrivedAtIntermediateOffice(postOfficeId, shipmentId);

        
        assertEquals(ShipmentStatus.ARRIVED_AT_INTERMEDIATE_OFFICE, updatedShipment.getStatus());
        verify(shipmentRepository, times(1)).findById(shipmentId);
        verify(postOfficeService, times(1)).findById(postOfficeId);
        verify(shipmentRepository, times(1)).save(shipment);
        verify(shipmentHistoryRepository, times(1)).save(any(ShipmentHistory.class));
    }

    @Test
    void departedFromOffice_ShouldReturnUpdatedShipment() {
        
        Long postOfficeId = 1L;
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        PostOffice postOffice = new PostOffice();
        when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(shipment));
        when(postOfficeService.findById(postOfficeId)).thenReturn(postOffice);
        when(shipmentRepository.save(shipment)).thenReturn(shipment);

       
        Shipment updatedShipment = shipmentService.departedFromOffice(postOfficeId, shipmentId);

        
//        assertEquals(postOffice.getAddress(), updatedShipment.getDepartureLocation());
        verify(shipmentRepository, times(1)).findById(shipmentId);
        verify(postOfficeService, times(1)).findById(postOfficeId);
        verify(shipmentRepository, times(1)).save(shipment);
        verify(shipmentHistoryRepository, times(1)).save(any(ShipmentHistory.class));
    }

    @Test
    void receivedByRecipient_ShouldReturnUpdatedShipment() {
        
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        shipment.setStatus(ShipmentStatus.RECEIVED_BY_RECIPIENT);
        when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(shipment));
        when(shipmentRepository.save(shipment)).thenReturn(shipment);

       
        Shipment updatedShipment = shipmentService.receivedByRecipient(shipmentId);

        
        assertEquals(ShipmentStatus.RECEIVED_BY_RECIPIENT, updatedShipment.getStatus());
        verify(shipmentRepository, times(1)).findById(shipmentId);
        verify(shipmentRepository, times(1)).save(shipment);
        verify(shipmentHistoryRepository, times(1)).save(any(ShipmentHistory.class));
    }

    @Test
    void getShipmentHistory_ShouldReturnShipmentHistory() {
        
        Long shipmentId = 1L;
        List<ShipmentHistory> shipmentHistories = new ArrayList<>();
        shipmentHistories.add(new ShipmentHistory());
        shipmentHistories.add(new ShipmentHistory());
        when(shipmentHistoryRepository.findAllByShipmentId(shipmentId)).thenReturn(shipmentHistories);

       
        List<ShipmentHistory> retrievedShipmentHistories = shipmentService.getShipmentHistory(shipmentId);

        
        assertEquals(shipmentHistories, retrievedShipmentHistories);
        verify(shipmentHistoryRepository, times(1)).findAllByShipmentId(shipmentId);
    }

}