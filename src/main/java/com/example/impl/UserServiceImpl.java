
package com.example.impl;

import com.example.model.Schedule;
import com.example.model.UserDtls;
import com.example.model.TeacherRegistration;
import com.example.repository.ScheduleRepository;
import com.example.repository.UserRepository;
import com.example.repository.TeacherRegistrationRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRegistrationRepository teacherRegistrationRepository;

    @Override
    public UserDtls saveUser(UserDtls user) {
        return userRepository.save(user);
    }

    @Autowired
    private ScheduleRepository scheduleRepository;



    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
    @Override
    public UserDtls getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDtls getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDtls> getAllUsers(String role) {
        if (role == null || role.trim().isEmpty()) {
            return userRepository.findAll(); // Возвращает всех пользователей
        }
        return userRepository.findByRole(role); // Возвращает по роли, если указана
    }


    @Override
    public Boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveTeacherRegistration(TeacherRegistration registration) {
        registration.setRegistrationDate(LocalDateTime.now());
        teacherRegistrationRepository.save(registration);
    }

    @Override
    public List<TeacherRegistration> getAllRegistrations() {
        return teacherRegistrationRepository.findAllByOrderByRegistrationDateDesc();
    }

    @Override
    public TeacherRegistration getRegistrationById(Long id) {
        return teacherRegistrationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRegistrationById(Long id) {
        teacherRegistrationRepository.deleteById(id);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}

