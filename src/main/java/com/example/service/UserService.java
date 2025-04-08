package com.example.service;

import com.example.model.UserDtls;
import java.util.List;

public interface UserService {

    UserDtls saveUser(UserDtls user);
    UserDtls getUserByEmail(String email);
    UserDtls getUserById(Long id);
    List<UserDtls> getAllUsers(String role);
    Boolean existsEmail(String email); // Метод для проверки существования email
    void deleteUser(Long id);
    // Другие методы, если нужно


}
