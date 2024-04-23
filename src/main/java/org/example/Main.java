package org.example;

import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Appointment appointment = new Appointment();
        List<List<LocalTime>> output = appointment.getPossibleAppointments(Utils.loadJSON("calendar1.json"), Utils.loadJSON("calendar2.json"));
        for (List<LocalTime> tmp : output) {
            System.out.println(tmp.get(0));
            System.out.println(tmp.get(1));
        }
    }
}