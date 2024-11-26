package com.example.instructor_log.controllers;

import com.example.instructor_log.repository.UserRepository;
import com.example.instructor_log.services.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HistoryControllers {

    private final HistoryService historyService;
    private final UserRepository userRepository;

    @GetMapping("home/history")
    public String history() {
        return "history";
    }

    @PostMapping("home/history")
    public ResponseEntity<?> test(){
        List<?> map = historyService.getting_all_registered_users();
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @PostMapping("home/history/remove_user")
    public String remove_user(@RequestParam("id") int id){
        historyService.delete_user(id);
        return "history";//было "home"
    }

}
