package com.rania.microreservation.controller;

import com.rania.microreservation.dto.ReservationDto;
import com.rania.microreservation.entity.Reservation;
import com.rania.microreservation.service.IReservationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reservation")
public class ReservationController {

    private final IReservationService reservationService;

    private final ModelMapper modelMapper;
    /*private final IUserService iUserService;

    private final IEventService iEventService;*/
    @GetMapping("/getAll")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") int id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation) {
        try {
            // Calculer le nombre de jours
            int numberOfDays = reservationService.calculateNumberOfDays(reservation.getStartDate(), reservation.getEndDate());

            // Définir le nombre de jours dans l'objet Reservation
            reservation.setNbredays(numberOfDays);

            if (!reservationService.isValidReservationDates(reservation)) {
                return ResponseEntity.badRequest().body("Les dates de réservation ne sont pas valides.");
            }

            reservationService.addReservation(reservation);
            return ResponseEntity.ok("Réservation ajoutée avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }}

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") int id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Reservation addReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservationService.addReservation(reservation);
        return reservation;
    }}

