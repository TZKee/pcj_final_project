package com.example.appointmentservice.model;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSummary implements Serializable {
    @Id
    @NotNull
    private String id;

    @NotNull
    private Appointment appointment;

    @NotNull
    private User mechanic;

    @NotNull
    private String comment;

    @NotNull
    private Double totalCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentSummary that = (AppointmentSummary) o;
        return id.equals(that.id) && appointment.equals(that.appointment) && mechanic.equals(that.mechanic) && comment.equals(that.comment) && totalCost.equals(that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appointment, mechanic, comment, totalCost);
    }

    @Override
    public String toString() {
        return "AppointmentSummary{" +
                "id='" + id + '\'' +
                ", appointment=" + appointment +
                ", mechanic=" + mechanic +
                ", comment='" + comment + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}