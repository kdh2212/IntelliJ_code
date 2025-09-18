package com.du.reservation20250918.service;


import com.du.reservation20250918.domain.Reservation;
import com.du.reservation20250918.domain.ReservationItem;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private com.du.reservation20250918.dao.ReservationMapper reservationMapper;

    public List<Reservation> getAllReservations() {
        return reservationMapper.findAll();
    }

    public void addReservation(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    public void deleteReservation(Long id) {
        reservationMapper.delete(id);
    }

    public List<ReservationItem> getItemsByReservationId(Long reservationId) {
        return reservationMapper.findItemsByReservationId(reservationId);
    }

    public void addReservationItem(ReservationItem item) {
        reservationMapper.insertItem(item);
    }

    public void deleteReservationItem(Long id) {
        reservationMapper.deleteItem(id);
    }

    public Reservation getReservationById(Long id) {
        return reservationMapper.findById(id);
    }

}

