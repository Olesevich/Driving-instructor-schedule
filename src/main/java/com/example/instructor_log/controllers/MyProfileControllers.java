package com.example.instructor_log.controllers;

import com.example.instructor_log.services.MyProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MyProfileControllers {

    private final MyProfileService myProfileService;

    @GetMapping("home/myprofile")
    public String myprofile(Model model) {
        model.addAttribute("family_name",
                myProfileService.personal_data_family_name(myProfileService.user_avtorisirovan()));
        model.addAttribute("name",
                myProfileService.personal_data_name(myProfileService.user_avtorisirovan()));
        model.addAttribute("surname",
                myProfileService.personal_data_surname(myProfileService.user_avtorisirovan()));
        model.addAttribute("login",
                myProfileService.personal_data_login(myProfileService.user_avtorisirovan()));
        model.addAttribute("number_group",
                myProfileService.personal_data_number_group(myProfileService.user_avtorisirovan()));
        model.addAttribute("email",
                myProfileService.personal_data_email(myProfileService.user_avtorisirovan()));
        return "myprofile";
    }

    @PostMapping("personal_classes")
    public ResponseEntity<?> personal_classes(){
        List<?> map = myProfileService.personal_classes();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
