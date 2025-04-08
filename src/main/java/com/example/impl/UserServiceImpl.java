package com.example.impl;
import com.example.model.UserDtls;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDtls saveUser(UserDtls user) {
        return userRepository.save(user);
    }

    @Override
    public UserDtls getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDtls> getAllUsers(String role) {
// Если передана роль, фильтруем пользователей по роли
        if (role != null && !role.isEmpty()) {
            return userRepository.findByRole(role); // Метод для поиска пользователей по роли
        }
// Если роль не передана, возвращаем всех пользователей
        return userRepository.findAll();
    }

    // В классе UserServiceImpl
    @Override
    public UserDtls getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Или другая логика поиска по ID
    }

    // В классе UserServiceImpl
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id); // или аналогичный метод в зависимости от вашего репозитория
    }


    @Override
    public Boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}