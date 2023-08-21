package com.example.demo.controller;

import com.example.demo.entity.Shipment;
import com.example.demo.entity.ShipmentHistory;
import com.example.demo.entity.ShipmentStatus;
import com.example.demo.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<Shipment> registerShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.registerShipment(shipment);
        return ResponseEntity.created(URI.create("/api/shipment/" + createdShipment.getId())).body(createdShipment);
    }

    @PutMapping("/arrived/{postOfficeId}/{shipmentId}")
    public ResponseEntity<Shipment> arrivedAtIntermediateOffice(@PathVariable Long postOfficeId, @PathVariable Long shipmentId) {
        Shipment shipment = shipmentService.arrivedAtIntermediateOffice(postOfficeId, shipmentId);
        return shipment!= null? ResponseEntity.ok(shipment) : ResponseEntity.notFound().build();
    }

    @PutMapping("/departed/{postOfficeId}/{shipmentId}")
    public ResponseEntity<Shipment> departedFromOffice(@PathVariable Long postOfficeId, @PathVariable Long shipmentId) {
        Shipment shipment = shipmentService.departedFromOffice(postOfficeId, shipmentId);
        return shipment!= null? ResponseEntity.ok(shipment) : ResponseEntity.notFound().build();
    }

    @PutMapping("/received/{shipmentId}")
    public ResponseEntity<Shipment> receivedByRecipient(@PathVariable Long shipmentId) {
        Shipment shipment = shipmentService.receivedByRecipient(shipmentId);
        return shipment!= null? ResponseEntity.ok(shipment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<ShipmentStatus> getShipmentStatus(@PathVariable Long id) {
        Shipment shipment = shipmentService.findById(id);
        return shipment!= null? ResponseEntity.ok(shipment.getStatus()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<ShipmentHistory>> getShipmentHistory(@PathVariable Long id) {
        List<ShipmentHistory> shipmentHistory = shipmentService.getShipmentHistory(id);
        return shipmentHistory!= null? ResponseEntity.ok(shipmentHistory) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> findById(@PathVariable Long id) {
        Shipment shipment = shipmentService.findById(id);
        return shipment!= null? ResponseEntity.ok(shipment) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipment() {
        List<Shipment> shipments = shipmentService.getAllShipment();
        return shipments!= null? ResponseEntity.ok(shipments) : ResponseEntity.noContent().build();
    }
}
