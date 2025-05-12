package com.example.controller;

import com.example.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final TeacherService teacherService;

    public StatisticsController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/sections")
    public ResponseEntity<Map<String, Integer>> getSectionStatistics() {
        Map<String, Integer> stats = teacherService.getRegistrationStatisticsBySport();
        return ResponseEntity.ok(stats);
    }
}
