package com.example.instructor_log.mapper;

import com.example.instructor_log.dto.HistoryDTO;
import com.example.instructor_log.entity.MyUsers;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface HistoryMapper {

    List <HistoryDTO> toHistoryDTO(List<MyUsers> myUsers);
}
