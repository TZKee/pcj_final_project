package com.example.appointmentservice.datasource;

import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AppointmentPseudoDataBase {
    private List<Appointment> appointments;

    public AppointmentPseudoDataBase() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        appointments = Stream.of(
                new Appointment("1", "Fiat",
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        new User("1", "Vlad", "Mihail", "taca.vlad@gmail.com", "address for Taca Vlad", "mechanic")
                ),
                new Appointment("2", "Tesla",
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        new User("2","Andrei", "Andrei", "andrei.andrei@gmail.com", "address for Andrei Andrei", "car owner")
                ),
                new Appointment("3", "Dacia",
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        new User("3","John", "Smith", "john.smith@gmail.com", "address for John Smith", "car owner")
                ),
                new Appointment("4", "BMW",
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        LocalDateTime.parse("2022-03-11 16:59", formatter),
                        new User("4","Will", "Smith", "will.smith@gmail.com", "address for Will Smith", "mechanic")
                )
        ).collect(Collectors.toList());

    }

    public List<Appointment> getAllAppointments() {
        System.out.println("get appointment");
        return appointments;
    }

    public Optional<Appointment> findAppointmentById(String id) {
        return appointments.stream().filter(appointment -> appointment.getId().equals(id)).findFirst();
    }

    public Optional<Appointment> findAppointmentByStartDate(LocalDateTime startDate) {
        return appointments.stream().filter(appointment -> appointment.getStartDate().equals(startDate)).findFirst();
    }


    public Appointment saveAppointment(Appointment appointment) {
        System.out.println("save appointment");
        findAppointmentById(appointment.getId()).ifPresent(i -> {
            throw new RuntimeException("Appointment with id " + i.getId() + " already exists!");
        });
        findAppointmentByStartDate(appointment.getStartDate()).ifPresent(i -> {
            throw new RuntimeException("Appointment with start date " + i.getStartDate() + " already exists!");
        });
        this.appointments.add(appointment);

        return appointment;
    }

    public Appointment updateAppointment(Appointment appointment) {
        System.out.println("In 4002 update");
        Appointment existingAppointment = findAppointmentById(appointment.getId()).orElseThrow(
                () -> new RuntimeException("Appointment with id " + appointment.getId() + " not found!"));

        int index = appointments.indexOf(existingAppointment);

        appointments.get(index).setCar(appointment.getCar());
        appointments.get(index).setCarOwner(appointment.getCarOwner());
        appointments.get(index).setEndDate(appointment.getEndDate());
        appointments.get(index).setStartDate(appointment.getStartDate());

        return appointment;
    }

    public boolean deleteAppointment(String id) {
        Appointment userToDelete = findAppointmentById(id).orElseThrow(
                () -> new RuntimeException("Appointment with id " + id + " not found!"));

        return appointments.remove(userToDelete);
    }
}
