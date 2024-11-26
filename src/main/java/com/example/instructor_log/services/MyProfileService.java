package com.example.instructor_log.services;

import com.example.instructor_log.entity.MyUsers;
import com.example.instructor_log.mapper.MySchedulesMapper;
import com.example.instructor_log.repository.SchedulesRepository;
import com.example.instructor_log.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyProfileService {

    private final UserRepository userRepository;
    private final SchedulesRepository schedulesRepository;
    private final HttpServletRequest request;
    private final MySchedulesMapper mySchedulesMapper;

    private String personal_data_family_name(MyUsers users){
        return users.getFamilyName();
    }

    private String personal_data_name(MyUsers users){
        return users.getName();
    }

    private String personal_data_surname(MyUsers users){
        return users.getSurname();
    }

    private String personal_data_login(MyUsers users){
        return users.getLogin();
    }

    private int personal_data_number_group(MyUsers users){
        return users.getNumber_group();
    }

    private String personal_data_email(MyUsers users){
        return users.getEmail();
    }

    private MyUsers user_profil(int id){
        MyUsers myUsers = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return myUsers;
    }

    public int return_user_id(){
        MyUsers myUsers = userRepository.findByLogin(request.getUserPrincipal().getName())
                .orElseThrow(RuntimeException::new);
        return myUsers.getId();
    }

    public List<String> personal_classes(int id){
        MyUsers myUsers = user_profil(id);
        return schedulesRepository.findByMyUsers_Id(myUsers.getId()).stream().map(mySchedulesMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public void user_details(Model model, int id) {
        model.addAttribute("family_name",
                personal_data_family_name(user_profil(id)));
        model.addAttribute("name",
                personal_data_name(user_profil(id)));
        model.addAttribute("surname",
                personal_data_surname(user_profil(id)));
        model.addAttribute("login",
                personal_data_login(user_profil(id)));
        model.addAttribute("number_group",
                personal_data_number_group(user_profil(id)));
        model.addAttribute("email",
                personal_data_email(user_profil(id)));
    }

}
