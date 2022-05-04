package com.example.userservice.service;

import com.example.userservice.datasource.UserPseudoDataBase;
import com.example.userservice.request.CreateAppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    private final RestTemplate restTemplate;
    private final UserPseudoDataBase userDB;
    private final CircuitBreaker circuitBreaker;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    public AppointmentServiceImplementation(RestTemplate restTemplate, UserPseudoDataBase userDB, CircuitBreakerFactory circuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.userDB = userDB;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
    }

    private List<String> displayDefaultAppointmentRequest(String route, Throwable throwable) {
        System.out.println(throwable.toString());
        return Arrays.asList("Message from circuit breaker after trying to access " + route + " route!");
    }


    @Override
    public List<?> getAllAppointments() {
        return circuitBreaker.run(() -> Arrays.asList(restTemplate.getForEntity(
                "http://localhost:4002/api/appointment",
                CreateAppointmentRequest[].class).getBody()), throwable ->
                displayDefaultAppointmentRequest("getAllAppointments", throwable));
    }

    @Override
    public Optional<CreateAppointmentRequest> findAppointmentById(String id) {
        return getAllAppointments().stream().filter(appointment -> appointment instanceof CreateAppointmentRequest)
                .map(appointment -> (CreateAppointmentRequest) appointment)
                .filter(appointment -> appointment.getId().equals(id)).findFirst();
    }


    private CreateAppointmentRequest postForEntity(CreateAppointmentRequest appointment) {
        ResponseEntity<CreateAppointmentRequest> response = restTemplate.postForEntity(
                "http://localhost:4002/api/appointment",
                appointment,
                CreateAppointmentRequest.class);

        if (userDB.findUserById(appointment.getCarOwner().getId()).isEmpty())
            userDB.saveUser(appointment.getCarOwner());

        return response.getBody();
    }

    @Override
    public List<?> saveAppointment(CreateAppointmentRequest appointment) {

        return circuitBreaker.run(() -> Arrays.asList(postForEntity(appointment)), throwable ->
                displayDefaultAppointmentRequest("saveAppointment", throwable));

    }

    @Override
    public CreateAppointmentRequest updateAppointment(CreateAppointmentRequest appointment) {
        HttpEntity<CreateAppointmentRequest> request = new HttpEntity<>(appointment);

        ResponseEntity<CreateAppointmentRequest> response = restTemplate.exchange(
                "http://localhost:4002/api/appointment",
                HttpMethod.PUT,
                request,
                CreateAppointmentRequest.class
        );

        userDB.updateUser(appointment.getCarOwner());
        System.out.println(response);
        return response.getBody();
    }

    @Override
    public boolean deleteAppointment(String id) {
        if (findAppointmentById(id).isPresent()) {
            restTemplate.delete("http://localhost:4002/api/appointment/" + id);
            return true;
        } else {
            throw new RuntimeException("Appointment with id " + id + " not found!");
        }
    }
}
