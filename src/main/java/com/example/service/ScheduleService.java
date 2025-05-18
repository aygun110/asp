package com.example.service;

import com.example.model.Schedule;
import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    Schedule getScheduleById(Long id);
    List<Schedule> findByDisciplineName(String disciplineName); // Заменяем sportType на disciplineName
    List<Schedule> findByDayOfWeek(String dayOfWeek);
    List<String> getAllDisciplineNames(); // Переименовываем getAllSportTypes()
    List<String> getAllDaysOfWeek();
}