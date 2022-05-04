package com.example.appointmentservice.service;

import com.example.appointmentservice.datasource.AppointmentPseudoDataBase;
import com.example.appointmentservice.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImplementation implements AppointmentService{
    private final AppointmentPseudoDataBase appointmentDB;

    @Autowired
    public AppointmentServiceImplementation(AppointmentPseudoDataBase userDb) {
        this.appointmentDB = userDb;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDB.getAllAppointments();
    }

    @Override
    public Optional<Appointment> findAppointmentById(String id) {
        return appointmentDB.findAppointmentById(id);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentDB.saveAppointment(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentDB.updateAppointment(appointment);
    }

    @Override
    public boolean deleteAppointment(String id) {
        return appointmentDB.deleteAppointment(id);
    }
}
