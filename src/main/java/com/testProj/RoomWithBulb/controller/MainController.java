package com.testProj.RoomWithBulb.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.testProj.RoomWithBulb.domain.Room;
import com.testProj.RoomWithBulb.repo.RoomRepo;
import com.testProj.RoomWithBulb.utils.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private RoomRepo roomRepo;

    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) throws GeoIp2Exception {
        Iterable<Room> rooms = roomRepo.findAll();
        model.addAttribute("location", LocationUtils.getLocation(request));
        model.addAttribute("allCountries", LocationUtils.getAllCountries(Locale.ENGLISH));
        model.addAttribute("allRooms", rooms);
        return "main";
    }


    @PostMapping("/")
    public String add(@Valid Room room, BindingResult bindingResult, HttpServletRequest request,
                      Model model) throws GeoIp2Exception {
        if (room.getStatus() == 1 || room.getStatus() == 0) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
                model.mergeAttributes(errorsMap);
            } else {
                roomRepo.save(room);
                Iterable<Room> rooms = roomRepo.findAll();
                model.addAttribute("allRooms", rooms);
            }
        }
        Iterable<Room> rooms = roomRepo.findAll();
        model.addAttribute("location", LocationUtils.getLocation(request));
        model.addAttribute("allCountries", LocationUtils.getAllCountries(Locale.ENGLISH));
        model.addAttribute("allRooms", rooms);

        return "redirect:/";
    }

    @GetMapping("/room")
    public String room(
            @RequestParam(name = "id") Room room,
            Model model,
            HttpServletRequest request

    ) throws GeoIp2Exception, IOException {
        String answer = "";
        String CurrentLocation = LocationUtils.getLocation(request);
        model.addAttribute("location", CurrentLocation);
        model.addAttribute("liveStatus", room.getStatus());
        if (room.getCountry().equals(CurrentLocation)) {
            model.addAttribute("room", room);
            answer = "room";
        } else {
            answer = "denided";
        }
        return answer;
    }

    @PostMapping("/room")
    public String switchBulb(
            @RequestParam("status") Integer status,
            @RequestParam(name = "id") Room room,
            Model model
    ) {

        if (!StringUtils.isEmpty(status)) {
            room.setStatus(status);
        }
        roomRepo.save(room);
        model.addAttribute(room);

        return "redirect:/room?id=" + room.getId();
    }


}
