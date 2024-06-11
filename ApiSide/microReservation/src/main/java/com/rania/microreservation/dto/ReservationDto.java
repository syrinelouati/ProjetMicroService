package com.rania.microreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Integer idReservation;
    private int Nbredays;
    private Date startDate;
    private Date endDate;
    private String notes;
    private String username;
    private Integer idEvent;
}
