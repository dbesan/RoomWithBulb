package com.testProj.RoomWithBulb.controller;

import com.testProj.RoomWithBulb.domain.Room;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/room/status")
    public String getStatus(@RequestParam(name = "id") Room room,
                            Model model) {
        String answer = "";
        if (room.getStatus() == 1) {
            answer = "Turned on";
        } else if (room.getStatus() == 0) {
            answer = "Turned off";
        }
        return answer;
    }
}
