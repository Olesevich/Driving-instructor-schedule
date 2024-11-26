package com.example.instructor_log.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calendars")
public class MySchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @ManyToOne/*(cascade = CascadeType.ALL)*/
    @JoinColumn(name = "myuser_id")
    private MyUsers myUsers;

    @Column(name = "data")
    private String data;

    @Column(name = "time")
    private int time;

    @Column(name = "number_lesson")
    private String numberLesson;


}
