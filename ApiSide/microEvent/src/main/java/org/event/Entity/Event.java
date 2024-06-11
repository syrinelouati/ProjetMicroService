package org.event.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvent;
    private String name;

    @Column(length = 1000)
    private String description;

    private Date startDate;
    private Date endDate;
    private int nbParticipant;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    private double price;
    private String videoUrl;


    @Enumerated(EnumType.STRING)
    private EventCategory category;



}


