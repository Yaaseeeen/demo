package com.example.demo.repository;

import com.example.demo.entity.Shipment;
import com.example.demo.entity.ShipmentHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ShipmentHistoryRepositoryTest {

    @Autowired
    private ShipmentHistoryRepository shipmentHistoryRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Test
    void testSaveAndFindAllByShipmentId() {
        
        Shipment shipment = new Shipment();
        shipmentRepository.save(shipment);

        ShipmentHistory shipmentHistory1 = new ShipmentHistory();
        shipmentHistory1.setShipment(shipment);
        shipmentHistory1.setComment("Comment 1");
        shipmentHistory1.setLocation("Location 1");
        shipmentHistory1.setDate(LocalDateTime.now());

        ShipmentHistory shipmentHistory2 = new ShipmentHistory();
        shipmentHistory2.setShipment(shipment);
        shipmentHistory2.setComment("Comment 2");
        shipmentHistory2.setLocation("Location 2");
        shipmentHistory2.setDate(LocalDateTime.now());

       
        shipmentHistoryRepository.save(shipmentHistory1);
        shipmentHistoryRepository.save(shipmentHistory2);

        List<ShipmentHistory> shipmentHistories = shipmentHistoryRepository.findAllByShipmentId(shipment.getId());

        
        assertEquals(2, shipmentHistories.size());
        assertEquals(shipmentHistory1, shipmentHistories.get(0));
        assertEquals(shipmentHistory2, shipmentHistories.get(1));
    }

    // Write similar tests for other repository methods if needed

}