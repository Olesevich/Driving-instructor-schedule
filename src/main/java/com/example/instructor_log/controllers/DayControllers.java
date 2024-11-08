package com.example.instructor_log.controllers;

import com.example.instructor_log.services.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class DayControllers {

    private final DayService dayService;

    @PostMapping("push_time")
    public String day(@RequestParam("time") int time) {
        dayService.setTime(time);//при нажатии кнопки со временем отпрв данные дня и месяца
        dayService.saving_data_bd();
        return "day";
    }

    @PostMapping("load_date")
    public ResponseEntity<?> loadDate(@RequestParam("day") String day,
                                   @RequestParam("month") String month,
                                   @RequestParam("year") String year) {
        dayService.setData(day + "." + month + "." + year);//при заходе на страницу сразу идет отпр данных и записить их врем. в сер.
        HashMap<Integer,String> map = dayService.search_time_by_day();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
