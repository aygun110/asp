package com.example.impl;

import com.example.model.Schedule;
import com.example.repository.ScheduleRepository;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
    }

    @Override
    public List<Schedule> findByDisciplineName(String disciplineName) {
        return scheduleRepository.findAll().stream()
                .filter(schedule ->
                        schedule.getDiscipline() != null &&
                                schedule.getDiscipline().getName().equalsIgnoreCase(disciplineName)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Schedule> findByDayOfWeek(String dayOfWeek) {
        return scheduleRepository.findByDayOfWeek(dayOfWeek);
    }


    @Override
    public List<String> getAllDisciplineNames() {
        return scheduleRepository.findAll().stream()
                .filter(schedule -> schedule.getDiscipline() != null)
                .map(schedule -> schedule.getDiscipline().getName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllDaysOfWeek() {
        return scheduleRepository.findAll().stream()
                .map(Schedule::getDayOfWeek)
                .distinct()
                .collect(Collectors.toList());
    }
}