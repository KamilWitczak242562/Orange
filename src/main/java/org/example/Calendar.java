package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Calendar {
    private LocalTime workStart;
    private LocalTime workEnd;
    private List<List<LocalTime>> appointmentsTimes = new ArrayList<>();

    public void addAppointmentTime(List<LocalTime> appointmentTime) {
        appointmentsTimes.add(appointmentTime);
    }
}
