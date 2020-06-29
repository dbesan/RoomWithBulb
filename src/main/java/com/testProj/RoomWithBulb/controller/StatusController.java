package com.testProj.RoomWithBulb.controller;

import com.testProj.RoomWithBulb.domain.Room;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/room/{room}/status")
    public int getStatus(@PathVariable Room room,
                         Model model) {
        return room.getStatus();
    }
}
