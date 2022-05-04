package com.example.userservice.service;

import com.example.userservice.request.CreateAppointmentRequest;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<?> getAllAppointments();

    Optional<CreateAppointmentRequest> findAppointmentById(String id);

    List<?> saveAppointment(CreateAppointmentRequest appointment);

    CreateAppointmentRequest updateAppointment(CreateAppointmentRequest appointment);

    boolean deleteAppointment(String id);
}
