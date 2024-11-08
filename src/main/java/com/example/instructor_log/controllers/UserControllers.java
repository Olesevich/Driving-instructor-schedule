package com.example.instructor_log.controllers;

import com.example.instructor_log.repository.UserRepository;
import com.example.instructor_log.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserControllers {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
                model.addAttribute("error", error != null);
                model.addAttribute("logout", logout != null);
        return "registration";
    }

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("home/day")
    public String day(){
        return "day";
    }


    @PostMapping("/registrat")
    public String registration(@RequestParam("login") String login,
                               @RequestParam("password") String password,
                               @RequestParam("povt_password" ) String povt_password,
                               @RequestParam("email") String email,
                               @RequestParam("name") String name,
                               @RequestParam("familyname") String familyName,
                               @RequestParam("surname") String surname,
                               @RequestParam("number_group") int number_group) {
        if(password.equals(povt_password)){
            userService.updateUser(login,password, email,name,familyName,surname,number_group);
            return "registration";
        }
        else {
            return "registration";//в будущем надо дописать что бы выскакивала ошибка то что пароль не верный
        }

    }

}
