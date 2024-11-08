package com.example.instructor_log.services;

import com.example.instructor_log.constants.Constants;
import com.example.instructor_log.entity.MySchedules;
import com.example.instructor_log.entity.MyUsers;
import com.example.instructor_log.repository.SchedulesRepository;
import com.example.instructor_log.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DayService {

    private String data;
    private int time;

    private final SchedulesRepository schedulesRepository;
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    public void setData(String data) {
        this.data = data;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public void saving_data_bd() {//сохраняет в бд значение даты и времени занятия учащегося авторизированого
        MySchedules mySchedules = new MySchedules();
        MyUsers myUsers = userRepository.findByLogin(request.getUserPrincipal().getName())//получ имени авториз челов
                .orElseThrow(RuntimeException::new);

        if (check_for_maximum_number_of_classes(myUsers)) {
            mySchedules.setTime(time);
            mySchedules.setData(data);
            mySchedules.setMyUsers(myUsers);
            mySchedules.setNumberLesson(recording_exercises_in_database(myUsers));
            schedulesRepository.save(mySchedules);
        }
        else {
            //надо дописать что бы ошибка вылетала то что уже количество занятие больше, чем положено
        }
    }

    public HashMap<Integer,String> search_time_by_day(){
        HashMap<Integer,String> map = new HashMap<>();
        List<MySchedules> mydata = schedulesRepository.findByData(data);
        for (MySchedules mySchedules : mydata) {
            map.put(mySchedules.getTime(), mySchedules.getMyUsers().getFamilyName());
        }
        for (Map.Entry<Integer,String> entry : map.entrySet()) {//для проверки и вывода в консоль
//            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return map;
    }

    public String recording_exercises_in_database(MyUsers myUsers){//сохраняет в бд номер упражнения привязанный к дате
        int number_lesson = number_of_classes(myUsers);
        return Constants.EXERCISE_NUMBER[number_lesson];
    }

    public boolean check_for_maximum_number_of_classes(MyUsers myUsers){
        if (Constants.EXERCISE_NUMBER.length > number_of_classes(myUsers)){
            return true;
        }
        else {
            return false;
        }
    }

    public int number_of_classes(MyUsers myUsers){
        List<MySchedules> mydata = schedulesRepository.findByMyUsers_Id(myUsers.getId());
        return mydata.size();
    }


}
