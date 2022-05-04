package com.example.appointmentservice.service;

import com.example.appointmentservice.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    Optional<Appointment> findAppointmentById(String id);

    Appointment saveAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    boolean deleteAppointment(String id);
}
