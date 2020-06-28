package com.testProj.RoomWithBulb.controller;

import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.testProj.RoomWithBulb.domain.Room;
import com.testProj.RoomWithBulb.repo.RoomRepo;
import com.testProj.RoomWithBulb.service.RawDBDemoGeoIPLocationService;
import com.testProj.RoomWithBulb.utils.CountryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String main(Model model, HttpServletRequest request) throws IOException, GeoIp2Exception {
        Iterable<Room> rooms = roomRepo.findAll();
        String location = "";
        String ip = request.getRemoteAddr();
        try {
            location = RawDBDemoGeoIPLocationService.getLocation(ip);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressNotFoundException e) {
            location = "The address: " + ip + " is not in the database.";
        }
        model.addAttribute("allCountries", CountryList.getAllCountries(Locale.ENGLISH));
        model.addAttribute("location", location);
        model.addAttribute("allRooms", rooms);
        return "main";
    }

    @PostMapping("/")
    public String add(@Valid Room room, BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            roomRepo.save(room);
            Iterable<Room> rooms = roomRepo.findAll();
            model.addAttribute("allRooms", rooms);
        }

        Iterable<Room> rooms = roomRepo.findAll();
        model.addAttribute("allCountries", CountryList.getAllCountries(Locale.ENGLISH));
        model.addAttribute("allRooms", rooms);

        return "main";
    }

    @GetMapping("/room/{room}")
    public String room(
            @PathVariable Room room,
            Model model

    ) {


        model.addAttribute("room", room);

        return "room";
    }

    @PostMapping("/room/{room}")
    public String switchBulb(
            @RequestParam("status") Integer status,
            @PathVariable Room room,
            Model model
    ) {
        if (!StringUtils.isEmpty(status)) {
            room.setStatus(status);
        }
        roomRepo.save(room);


        return "room";
    }


}
