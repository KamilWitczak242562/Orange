package org.example;

import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    public List<List<LocalTime>> getPossibleAppointments(Calendar calendar1, Calendar calendar2) {
        List<List<LocalTime>> output = new ArrayList<>();
        LocalTime start = checkLater(calendar1.getWorkStart(), calendar2.getWorkStart());
        LocalTime end = checkEarlier(calendar1.getWorkEnd(), calendar2.getWorkEnd());
        List<List<LocalTime>> times = createTimeList(start, end, Utils.DURATION);

        for (List<LocalTime> interval : times) {
            boolean toDelete = false;

            for (List<LocalTime> intervalToDelete : calendar1.getAppointmentsTimes()) {
                if (doTheyIntersect(interval, intervalToDelete)) {
                    toDelete = true;
                    break;
                }
            }

            if (!toDelete) {
                for (List<LocalTime> intervalToDelete : calendar2.getAppointmentsTimes()) {
                    if (doTheyIntersect(interval, intervalToDelete)) {
                        toDelete = true;
                        break;
                    }
                }
            }

            if (!toDelete) {
                output.add(interval);
            }
        }
        return output;
    }

    public boolean doTheyIntersect(List<LocalTime> interval1, List<LocalTime> interval2) {
        return interval1.get(0).compareTo(interval2.get(1)) < 0 &&
                interval1.get(1).compareTo(interval2.get(0)) > 0;
    }

    public LocalTime checkEarlier(LocalTime time1, LocalTime time2) {
        if (time1.isBefore(time2)) {
            return time1;
        } else {
            return time2;
        }
    }

    public LocalTime checkLater(LocalTime time1, LocalTime time2) {
        if (time1.isAfter(time2)) {
            return time1;
        } else {
            return time2;
        }
    }

    public List<List<LocalTime>> createTimeList(LocalTime startTime, LocalTime endTime, int minutesInterval) {
        List<List<LocalTime>> timeList = new ArrayList<>();

        LocalTime currentTime = startTime;
        while (currentTime.isBefore(endTime)) {
            List<LocalTime> tmp = new ArrayList<>();
            tmp.add(currentTime);
            tmp.add(currentTime.plusMinutes(minutesInterval));
            timeList.add(tmp);
            currentTime = currentTime.plusMinutes(minutesInterval);
        }

        return timeList;
    }

}
