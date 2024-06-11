package com.rania.microreservation.service;
import com.rania.microreservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.sql.Date;
public interface IReservationService {
    List<Reservation> getAllReservations();
    Reservation getReservationById (int id);
    void addReservation(Reservation reservation);

    void deleteReservation(int id);
    boolean isValidReservationDates(Reservation reservation);
    int calculateNumberOfDays(Date startDate, Date endDate);


}
