package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final int DURATION = 30;

    public static Calendar loadJSON(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        Calendar calendar = new Calendar();
        try {
            File file = new File(path);
            JsonNode rootNode = objectMapper.readTree(file);

            JsonNode workingHoursNode = rootNode.get("working_hours");
            calendar.setWorkStart(LocalTime.parse(workingHoursNode.get("start").asText()));
            calendar.setWorkEnd(LocalTime.parse(workingHoursNode.get("end").asText()));

            JsonNode plannedMeetingsNode = rootNode.get("planned_meeting");
            List<List<LocalTime>> appointments = new ArrayList<>();
            for (JsonNode meetingNode : plannedMeetingsNode) {
                List<LocalTime> tmp = new ArrayList<>() {{
                    add(LocalTime.parse(meetingNode.get("start").asText()));
                    add(LocalTime.parse(meetingNode.get("end").asText()));
                }};
                appointments.add(tmp);
            }
            calendar.setAppointmentsTimes(appointments);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
