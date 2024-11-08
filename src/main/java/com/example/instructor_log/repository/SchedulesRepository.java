package com.example.instructor_log.repository;

import com.example.instructor_log.entity.MySchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SchedulesRepository extends JpaRepository<MySchedules,Integer> {

    List<MySchedules> findByData(String data);
    List<MySchedules> findByMyUsers_Id(int id);
}
