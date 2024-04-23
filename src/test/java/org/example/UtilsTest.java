package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    public void testLoadCalendar1() {
        //given
        Calendar calendar;
        //when
        calendar = Utils.loadJSON("calendar1.json");
        //assert
        assertNotNull(calendar);
        assertEquals(LocalTime.parse("09:00"), calendar.getWorkStart());
        assertEquals(LocalTime.parse("19:55"), calendar.getWorkEnd());
        assertEquals(3, calendar.getAppointmentsTimes().size());
        assertEquals(LocalTime.parse("09:00"), calendar.getAppointmentsTimes().get(0).get(0));
        assertEquals(LocalTime.parse("10:30"), calendar.getAppointmentsTimes().get(0).get(1));
        assertEquals(LocalTime.parse("12:00"), calendar.getAppointmentsTimes().get(1).get(0));
        assertEquals(LocalTime.parse("13:00"), calendar.getAppointmentsTimes().get(1).get(1));
        assertEquals(LocalTime.parse("16:00"), calendar.getAppointmentsTimes().get(2).get(0));
        assertEquals(LocalTime.parse("18:00"), calendar.getAppointmentsTimes().get(2).get(1));
    }

    @Test
    public void testLoadCalendar2() {
        //given
        Calendar calendar;
        //when
        calendar = Utils.loadJSON("calendar2.json");
        //assert
        assertNotNull(calendar);
        assertEquals(LocalTime.parse("10:00"), calendar.getWorkStart());
        assertEquals(LocalTime.parse("18:30"), calendar.getWorkEnd());
        assertEquals(4, calendar.getAppointmentsTimes().size());
        assertEquals(LocalTime.parse("10:00"), calendar.getAppointmentsTimes().get(0).get(0));
        assertEquals(LocalTime.parse("11:30"), calendar.getAppointmentsTimes().get(0).get(1));
        assertEquals(LocalTime.parse("12:30"), calendar.getAppointmentsTimes().get(1).get(0));
        assertEquals(LocalTime.parse("14:30"), calendar.getAppointmentsTimes().get(1).get(1));
        assertEquals(LocalTime.parse("14:30"), calendar.getAppointmentsTimes().get(2).get(0));
        assertEquals(LocalTime.parse("15:00"), calendar.getAppointmentsTimes().get(2).get(1));
        assertEquals(LocalTime.parse("16:00"), calendar.getAppointmentsTimes().get(3).get(0));
        assertEquals(LocalTime.parse("17:00"), calendar.getAppointmentsTimes().get(3).get(1));
    }
}