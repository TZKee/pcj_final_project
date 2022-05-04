package com.example.userservice.request;

import com.example.userservice.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {
    private String id;
    private String car;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private User carOwner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAppointmentRequest that = (CreateAppointmentRequest) o;
        return id.equals(that.id) && car.equals(that.car) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && carOwner.equals(that.carOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, startDate, endDate, carOwner);
    }

    @Override
    public String toString() {
        return "CreateAppointmentRequest{" +
                "id='" + id + '\'' +
                ", car='" + car + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", carOwner=" + carOwner +
                '}';
    }
}
