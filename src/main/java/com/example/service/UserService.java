package com.example.service;

import com.example.model.UserDtls;
import java.util.List;

public interface UserService {

    UserDtls saveUser(UserDtls user);

    UserDtls getUserByEmail(String email);

    List<UserDtls> getUsers(String role);

    Boolean existsEmail(String email); // Метод для проверки существования email

    // Другие методы, если нужно
}
