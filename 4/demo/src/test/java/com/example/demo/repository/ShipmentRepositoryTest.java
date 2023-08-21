package com.example.demo.repository;

import com.example.demo.entity.MailType;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.ShipmentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ShipmentRepositoryTest {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Test
    void testSaveAndFindAll() {

        Shipment shipment1 = new Shipment();
        shipment1.setMailType(MailType.LETTER);
        shipment1.setRecipientIndex("12345");
        shipment1.setRecipientAddress("Address 1");
        shipment1.setRecipientName("Recipient 1");
        shipment1.setStatus(ShipmentStatus.REGISTERED);

        Shipment shipment2 = new Shipment();
        shipment2.setMailType(MailType.LETTER);
        shipment2.setRecipientIndex("67890");
        shipment2.setRecipientAddress("Address 2");
        shipment2.setRecipientName("Recipient 2");
        shipment2.setStatus(ShipmentStatus.ARRIVED_AT_INTERMEDIATE_OFFICE);

       
        shipmentRepository.save(shipment1);
        shipmentRepository.save(shipment2);

        List<Shipment> shipments = shipmentRepository.findAll();

        
        assertEquals(2, shipments.size());
        assertEquals(shipment1, shipments.get(0));
        assertEquals(shipment2, shipments.get(1));
    }
}