package com.example.repository;

import com.example.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



public interface UserRepository extends JpaRepository<UserDtls, Long> {
    // Поиск пользователей по роли
    List<UserDtls> findByRole(String role);

    // Поиск пользователя по email
    UserDtls findByEmail(String email);

    // Проверка существования пользователя по email
    Boolean existsByEmail(String email);
}