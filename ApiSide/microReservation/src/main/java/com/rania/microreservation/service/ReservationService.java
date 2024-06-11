package com.rania.microreservation.service;

import com.rania.microreservation.entity.Reservation;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rania.microreservation.repository.ReservationRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }




    @Override
    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);

    }

    @Override
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);

    }
    @Override
    public boolean isValidReservationDates(Reservation reservation) {
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();

        // Vérifier que la date de début est antérieure à la date de fin
        return startDate != null && endDate != null && startDate.before(endDate);
    }
    @Override
    public int calculateNumberOfDays(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) diffInDays;
    }

}



