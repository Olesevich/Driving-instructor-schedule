package com.example.instructor_log.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class MyUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name= "login"/*, unique = true*/)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "familyname")
    private String familyName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "number_group")
    private int number_group;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "myUsers", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MySchedules> mySchedules = new ArrayList<>();
}
