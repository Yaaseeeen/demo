package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class ShipmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "id")
    @JsonIgnore
    private Shipment shipment;
    private String comment;
    private String location;
    private LocalDateTime date;

}