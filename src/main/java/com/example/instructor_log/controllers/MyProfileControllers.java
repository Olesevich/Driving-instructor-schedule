package com.example.instructor_log.controllers;

import com.example.instructor_log.services.MyProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MyProfileControllers {

    private final MyProfileService myProfileService;

    @GetMapping("home/myprofile")
    public String myprofile(Model model) {
        int userId = myProfileService.return_user_id();
        myProfileService.user_details(model,userId);
        return "myprofile";
    }

    @PostMapping("personal_clas")
    public ResponseEntity<?> personal_classes(){
        int userId = myProfileService.return_user_id();
        List<?> map = myProfileService.personal_classes(userId);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("home/myprof")
    public String myprofiles(Model model, @RequestParam("id") int id) {
        myProfileService.user_details(model,id);
        return "myprofile";
    }

    @PostMapping("personal_classes")
    public ResponseEntity<?> personal_classes(int id){
        List<?> map = myProfileService.personal_classes(id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("role")
    public ResponseEntity<?> role(){
        return new ResponseEntity<>(myProfileService.return_role(), HttpStatus.OK);
    }

}
