package com.example.instructor_log.mapper;

import com.example.instructor_log.dto.MySchedulesDTO;
import com.example.instructor_log.entity.MySchedules;
import org.springframework.stereotype.Service;

@Service
public class MySchedulesMapper {

    public String mapToDTO(MySchedules mySchedules) {
        return mySchedules.getData();
    }
}
