package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    private Appointment appointment;

    @BeforeEach
    public void init() {
        appointment = new Appointment();
    }

    @Test
    public void getPossibleAppointments() {
        //given
        Calendar calendar1 = new Calendar();
        calendar1.setWorkStart(LocalTime.of(9, 0));
        calendar1.setWorkEnd(LocalTime.of(17, 0));
        calendar1.addAppointmentTime(Arrays.asList(LocalTime.of(10, 0), LocalTime.of(11, 0)));
        calendar1.addAppointmentTime(Arrays.asList(LocalTime.of(14, 0), LocalTime.of(15, 0)));

        Calendar calendar2 = new Calendar();
        calendar2.setWorkStart(LocalTime.of(10, 0));
        calendar2.setWorkEnd(LocalTime.of(18, 0));
        calendar2.addAppointmentTime(Arrays.asList(LocalTime.of(11, 0), LocalTime.of(12, 0)));
        calendar2.addAppointmentTime(Arrays.asList(LocalTime.of(15, 0), LocalTime.of(16, 0)));

        //when
        List<List<LocalTime>> result = appointment.getPossibleAppointments(calendar1, calendar2);

        //assert
        assertEquals(6, result.size());

        assertEquals(LocalTime.of(12, 0), result.get(0).get(0));
        assertEquals(LocalTime.of(12, 30), result.get(0).get(1));

        assertEquals(LocalTime.of(12, 30), result.get(1).get(0));
        assertEquals(LocalTime.of(13, 0), result.get(1).get(1));

        assertEquals(LocalTime.of(13, 0), result.get(2).get(0));
        assertEquals(LocalTime.of(13, 30), result.get(2).get(1));

        assertEquals(LocalTime.of(13, 30), result.get(3).get(0));
        assertEquals(LocalTime.of(14, 0), result.get(3).get(1));

        assertEquals(LocalTime.of(16, 0), result.get(4).get(0));
        assertEquals(LocalTime.of(16, 30), result.get(4).get(1));

        assertEquals(LocalTime.of(16, 30), result.get(5).get(0));
        assertEquals(LocalTime.of(17, 0), result.get(5).get(1));
    }

    @Test
    void doTheyIntersect() {
        //given
        List<LocalTime> interval1 = Arrays.asList(LocalTime.of(10, 0), LocalTime.of(12, 0));
        List<LocalTime> interval2 = Arrays.asList(LocalTime.of(11, 0), LocalTime.of(13, 0));
        List<LocalTime> interval3 = Arrays.asList(LocalTime.of(8, 0), LocalTime.of(9, 0));
        List<LocalTime> interval4 = Arrays.asList(LocalTime.of(13, 30), LocalTime.of(14, 0));

        //when & assert
        assertTrue(appointment.doTheyIntersect(interval1, interval2));
        assertTrue(appointment.doTheyIntersect(interval2, interval1));

        assertFalse(appointment.doTheyIntersect(interval1, interval3));
        assertFalse(appointment.doTheyIntersect(interval3, interval1));

        assertFalse(appointment.doTheyIntersect(interval1, interval4));
        assertFalse(appointment.doTheyIntersect(interval4, interval1));
    }

    @Test
    void checkEarlier() {
        //given
        LocalTime earlierTime = LocalTime.of(10, 30);
        LocalTime laterTime = LocalTime.of(12, 0);

        //when
        LocalTime result = appointment.checkEarlier(earlierTime, laterTime);

        //assert
        assertEquals(earlierTime, result);
    }

    @Test
    void checkLater() {
        //given
        LocalTime laterTime = LocalTime.of(12, 0, 0);
        LocalTime earlierTime = LocalTime.of(10, 0, 0);

        //when
        LocalTime result1 = appointment.checkLater(laterTime, earlierTime);

        //assert
        assertEquals(laterTime, result1);
    }

    @Test
    void createTimeList() {
        //given
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(10, 0);
        int minutesInterval = 15;

        //when
        List<List<LocalTime>> result = appointment.createTimeList(startTime, endTime, minutesInterval);

        //assert
        assertEquals(4, result.size());

        assertEquals(LocalTime.of(9, 0), result.get(0).get(0));
        assertEquals(LocalTime.of(9, 15), result.get(0).get(1));

        assertEquals(LocalTime.of(9, 15), result.get(1).get(0));
        assertEquals(LocalTime.of(9, 30), result.get(1).get(1));

        assertEquals(LocalTime.of(9, 30), result.get(2).get(0));
        assertEquals(LocalTime.of(9, 45), result.get(2).get(1));

        assertEquals(LocalTime.of(9, 45), result.get(3).get(0));
        assertEquals(LocalTime.of(10, 0), result.get(3).get(1));
    }
}