package com.example.repository;

import com.example.model.Schedule;
import com.example.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Теперь ищем по дисциплине
    List<Schedule> findByDiscipline(Discipline discipline);

    // Оставляем поиск по дню недели
    List<Schedule> findByDayOfWeek(String dayOfWeek);
}
