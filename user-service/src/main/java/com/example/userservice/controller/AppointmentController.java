package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.request.CreateAppointmentRequest;
import com.example.userservice.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointmentService")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveAppointment(@RequestBody CreateAppointmentRequest appointment) {
        System.out.println(appointment);


        try {
            return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody CreateAppointmentRequest appointment) {
        try {
            return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable String appointmentId) {
        try {
            return ResponseEntity.ok(appointmentService.deleteAppointment(appointmentId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
