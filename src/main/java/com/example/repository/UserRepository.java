package com.example.repository;

import com.example.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



public interface UserRepository extends JpaRepository<UserDtls, Integer> {

    public UserDtls findByEmail(String email);

    public List<UserDtls> findByRole(String role);

    public Boolean existsByEmail(String email);
}