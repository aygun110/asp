package com.example.repository;

import com.example.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    List<Discipline> findAllByOrderByNameAsc();
}