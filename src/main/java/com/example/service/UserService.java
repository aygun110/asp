package com.example.service;

import com.example.model.TeacherRegistration;
import com.example.model.UserDtls;

import java.util.List;

public interface UserService {

    UserDtls saveUser(UserDtls user);
    UserDtls getUserByEmail(String email);
    UserDtls getUserById(Long id);
    List<UserDtls> getAllUsers(String role);
    Boolean existsEmail(String email);
    void deleteUser(Long id);

    void saveTeacherRegistration(TeacherRegistration registration);
    List<TeacherRegistration> getAllRegistrations();

    TeacherRegistration getRegistrationById(Long id);
    void deleteRegistrationById(Long id);
}
