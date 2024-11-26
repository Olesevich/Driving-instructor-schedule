package com.example.instructor_log.services;

import com.example.instructor_log.dto.HistoryDTO;
import com.example.instructor_log.entity.MyUsers;
import com.example.instructor_log.mapper.HistoryMapper;
import com.example.instructor_log.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryMapper historyMapper;
    private final UserRepository userRepository;

    public List<HistoryDTO> getting_all_registered_users(){
        List<MyUsers> users = userRepository.findAll();
        return historyMapper.toHistoryDTO(users);
    }

    public void delete_user(int id){
        MyUsers myUsers = userRepository.findById(id).orElseThrow(RuntimeException::new);;
        userRepository.delete(myUsers);
    }
}
