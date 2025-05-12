package com.example.service;

import com.example.repository.TeacherRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    private final TeacherRegistrationRepository teacherRepository;

    public TeacherService(TeacherRegistrationRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Map<String, Integer> getRegistrationStatisticsBySport() {
        List<Object[]> results = teacherRepository.countRegistrationsBySportType();
        Map<String, Integer> stats = new HashMap<>();

        for (Object[] result : results) {
            String sportType = (String) result[0];
            Long count = (Long) result[1];
            stats.put(sportType, count.intValue());
        }

        return stats;
    }
}