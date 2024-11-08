package com.example.instructor_log.services;


import com.example.instructor_log.entity.MyUsers;
import com.example.instructor_log.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public void updateUser(String login, String password, String email, String name, String familyName,
                           String surname, int number_group) {
        MyUsers user = new MyUsers();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setFamilyName(familyName);
        user.setSurname(surname);
        user.setNumber_group(number_group);
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

}
