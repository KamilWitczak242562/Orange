package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


class CalendarTest {
    private Calendar calendar;

    @BeforeEach
    public void setUp() {
        List<LocalTime> times = new ArrayList<>() {{
           add(LocalTime.parse("10:00")); add(LocalTime.parse("12:00"));
        }};
        List<List<LocalTime>> listOfTimes = new ArrayList<>();
        listOfTimes.add(times);
        calendar = new Calendar(LocalTime.parse("08:00"), LocalTime.parse("16:00"),listOfTimes);
    }

    @Test
    void getWorkStart() {
        //given
        //when
        LocalTime workStart = calendar.getWorkStart();
        //assert
        assertEquals(LocalTime.parse("08:00"), workStart);
    }

    @Test
    void getWorkEnd() {
        //given
        //when
        LocalTime workEnd = calendar.getWorkEnd();
        //assert
        assertEquals(LocalTime.parse("16:00"), workEnd);
    }

    @Test
    void getAppointmentsTimes() {
        //given
        //when
        List<List<LocalTime>> times = calendar.getAppointmentsTimes();
        //assert
        assertEquals(1, times.size());
        assertEquals(2, times.get(0).size());
        assertEquals(LocalTime.parse("10:00"), times.get(0).get(0));
        assertEquals(LocalTime.parse("12:00"), times.get(0).get(1));
    }

    @Test
    void setWorkStart() {
        //given
        //when
        calendar.setWorkStart(LocalTime.parse("10:00"));
        //assert
        assertEquals(LocalTime.parse("10:00"), calendar.getWorkStart());
    }

    @Test
    void setWorkEnd() {
        //given
        //when
        calendar.setWorkEnd(LocalTime.parse("18:00"));
        //assert
        assertEquals(LocalTime.parse("18:00"), calendar.getWorkEnd());
    }

    @Test
    void setAppointmentsTimes() {
        //given
        List<LocalTime> times = new ArrayList<>() {{
            add(LocalTime.parse("12:00")); add(LocalTime.parse("14:00"));
        }};
        List<List<LocalTime>> listOfTimes = new ArrayList<>();
        listOfTimes.add(times);
        //when
        calendar.setAppointmentsTimes(listOfTimes);
        List<List<LocalTime>> tmp = calendar.getAppointmentsTimes();
        //assert
        assertEquals(1, tmp.size());
        assertEquals(2, tmp.get(0).size());
        assertEquals(LocalTime.parse("12:00"), tmp.get(0).get(0));
        assertEquals(LocalTime.parse("14:00"), tmp.get(0).get(1));
    }
}