package com.example.instructor_log.services;


import com.example.instructor_log.dto.MySchedulesDTO;
import com.example.instructor_log.entity.MySchedules;
import com.example.instructor_log.entity.MyUsers;
import com.example.instructor_log.mapper.MySchedulesMapper;
import com.example.instructor_log.repository.SchedulesRepository;
import com.example.instructor_log.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyProfileService {

    private final UserRepository userRepository;
    private final SchedulesRepository schedulesRepository;
    private final HttpServletRequest request;
    private final MySchedulesMapper mySchedulesMapper;

    public String personal_data_family_name(MyUsers users){
        return users.getFamilyName();
    }

    public String personal_data_name(MyUsers users){
        return users.getName();
    }

    public String personal_data_surname(MyUsers users){
        return users.getSurname();
    }

    public String personal_data_login(MyUsers users){
        return users.getLogin();
    }

    public int personal_data_number_group(MyUsers users){
        return users.getNumber_group();
    }

    public String personal_data_email(MyUsers users){
        return users.getEmail();
    }

    public MyUsers user_avtorisirovan(){
        MyUsers myUsers = userRepository.findByLogin(request.getUserPrincipal().getName())
                .orElseThrow(RuntimeException::new);
        return myUsers;
    }

    public List<String> personal_classes(){
        MyUsers myUsers = user_avtorisirovan();
        return schedulesRepository.findByMyUsers_Id(myUsers.getId()).stream().map(mySchedulesMapper::mapToDTO).collect(Collectors.toList());
    }


}
