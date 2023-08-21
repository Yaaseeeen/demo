package com.example.demo.controller;

import com.example.demo.entity.Shipment;
import com.example.demo.entity.ShipmentHistory;
import com.example.demo.entity.ShipmentStatus;
import com.example.demo.service.ShipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShipmentControllerTest {

    @Mock
    private ShipmentService shipmentService;

    private ShipmentController shipmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shipmentController = new ShipmentController(shipmentService);
    }

    @Test
    void registerShipment_ShouldReturnCreatedShipment() {
        
        Shipment shipment = new Shipment();
        Shipment createdShipment = new Shipment();
        when(shipmentService.registerShipment(shipment)).thenReturn(createdShipment);

       
        ResponseEntity<Shipment> response = shipmentController.registerShipment(shipment);

        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdShipment, response.getBody());
        assertEquals(URI.create("/api/shipment/" + createdShipment.getId()), response.getHeaders().getLocation());
        verify(shipmentService, times(1)).registerShipment(shipment);
    }

    @Test
    void arrivedAtIntermediateOffice_WithValidIds_ShouldReturnUpdatedShipment() {
        
        Long postOfficeId = 1L;
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        when(shipmentService.arrivedAtIntermediateOffice(postOfficeId, shipmentId)).thenReturn(shipment);

       
        ResponseEntity<Shipment> response = shipmentController.arrivedAtIntermediateOffice(postOfficeId, shipmentId);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shipment, response.getBody());
        verify(shipmentService, times(1)).arrivedAtIntermediateOffice(postOfficeId, shipmentId);
    }

    @Test
    void arrivedAtIntermediateOffice_WithInvalidIds_ShouldReturnNotFound() {
        
        Long postOfficeId = 1L;
        Long shipmentId = 1L;
        when(shipmentService.arrivedAtIntermediateOffice(postOfficeId, shipmentId)).thenReturn(null);

       
        ResponseEntity<Shipment> response = shipmentController.arrivedAtIntermediateOffice(postOfficeId, shipmentId);

        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shipmentService, times(1)).arrivedAtIntermediateOffice(postOfficeId, shipmentId);
    }

    @Test
    void departedFromOffice_WithValidIds_ShouldReturnUpdatedShipment() {
        
        Long postOfficeId = 1L;
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        when(shipmentService.departedFromOffice(postOfficeId, shipmentId)).thenReturn(shipment);

       
        ResponseEntity<Shipment> response = shipmentController.departedFromOffice(postOfficeId, shipmentId);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shipment, response.getBody());
        verify(shipmentService, times(1)).departedFromOffice(postOfficeId, shipmentId);
    }

    @Test
    void departedFromOffice_WithInvalidIds_ShouldReturnNotFound() {
        
        Long postOfficeId = 1L;
        Long shipmentId = 1L;
        when(shipmentService.departedFromOffice(postOfficeId, shipmentId)).thenReturn(null);

       
        ResponseEntity<Shipment> response = shipmentController.departedFromOffice(postOfficeId, shipmentId);

        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shipmentService, times(1)).departedFromOffice(postOfficeId, shipmentId);
    }

    @Test
    void receivedByRecipient_WithValidShipmentId_ShouldReturnUpdatedShipment() {
        
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        when(shipmentService.receivedByRecipient(shipmentId)).thenReturn(shipment);

       
        ResponseEntity<Shipment> response = shipmentController.receivedByRecipient(shipmentId);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shipment, response.getBody());
        verify(shipmentService, times(1)).receivedByRecipient(shipmentId);
    }

    @Test
    void receivedByRecipient_WithInvalidShipmentId_ShouldReturnNotFound() {
        
        Long shipmentId = 1L;
        when(shipmentService.receivedByRecipient(shipmentId)).thenReturn(null);

       
        ResponseEntity<Shipment> response = shipmentController.receivedByRecipient(shipmentId);

        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shipmentService, times(1)).receivedByRecipient(shipmentId);
    }

    @Test
    void getShipmentStatus_WithValidId_ShouldReturnShipmentStatus() {
        
        Long id = 1L;
        Shipment shipment = new Shipment();
        shipment.setStatus(ShipmentStatus.ARRIVED_AT_INTERMEDIATE_OFFICE);
        when(shipmentService.findById(id)).thenReturn(shipment);

       
        ResponseEntity<ShipmentStatus> response = shipmentController.getShipmentStatus(id);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ShipmentStatus.ARRIVED_AT_INTERMEDIATE_OFFICE, response.getBody());
        verify(shipmentService, times(1)).findById(id);
    }

    @Test
    void getShipmentStatus_WithInvalidId_ShouldReturnNotFound() {
        
        Long id = 1L;
        when(shipmentService.findById(id)).thenReturn(null);

       
        ResponseEntity<ShipmentStatus> response = shipmentController.getShipmentStatus(id);

        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shipmentService, times(1)).findById(id);
    }

    @Test
    void getShipmentHistory_WithValidId_ShouldReturnShipmentHistory() {
        
        Long id = 1L;
        List<ShipmentHistory> shipmentHistory = new ArrayList<>();
        shipmentHistory.add(new ShipmentHistory());
        shipmentHistory.add(new ShipmentHistory());
        when(shipmentService.getShipmentHistory(id)).thenReturn(shipmentHistory);

       
        ResponseEntity<List<ShipmentHistory>> response = shipmentController.getShipmentHistory(id);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shipmentHistory, response.getBody());
        verify(shipmentService, times(1)).getShipmentHistory(id);
    }

    @Test
    void getShipmentHistory_WithInvalidId_ShouldReturnNotFound() {
        
        Long id = 1L;
        when(shipmentService.getShipmentHistory(id)).thenReturn(null);

       
        ResponseEntity<List<ShipmentHistory>> response = shipmentController.getShipmentHistory(id);

        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shipmentService, times(1)).getShipmentHistory(id);
    }

    @Test
    void findById_WithValidId_ShouldReturnShipment() {
        
        Long id = 1L;
        Shipment shipment = new Shipment();
        when(shipmentService.findById(id)).thenReturn(shipment);

       
        ResponseEntity<Shipment> response = shipmentController.findById(id);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shipment, response.getBody());
        verify(shipmentService, times(1)).findById(id);
    }

    @Test
    void findById_WithInvalidId_ShouldReturnNotFound() {
        
        Long id = 1L;
        when(shipmentService.findById(id)).thenReturn(null);

       
        ResponseEntity<Shipment> response = shipmentController.findById(id);

        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shipmentService, times(1)).findById(id);
    }

    @Test
    void getAllShipment_ShouldReturnAllShipments() {
        
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment());
        shipments.add(new Shipment());
        when(shipmentService.getAllShipment()).thenReturn(shipments);

       
        ResponseEntity<List<Shipment>> response = shipmentController.getAllShipment();

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shipments, response.getBody());
        verify(shipmentService, times(1)).getAllShipment();
    }
}