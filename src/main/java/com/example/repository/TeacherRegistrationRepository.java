package com.example.repository;

import com.example.model.TeacherRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRegistrationRepository extends JpaRepository<TeacherRegistration, Long> {

    List<TeacherRegistration> findAllByOrderByRegistrationDateDesc();

    @Query("SELECT t.sportType as sport, COUNT(t) as count FROM TeacherRegistration t GROUP BY t.sportType")
    List<Object[]> countRegistrationsBySportType();
}
