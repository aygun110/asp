package com.example.controller;

import com.example.model.Discipline;
import com.example.model.Schedule;
import com.example.repository.DisciplineRepository;
import com.example.service.ScheduleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final UserService userService;
    private final ScheduleService scheduleService;
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public ScheduleController(UserService userService,
                              ScheduleService scheduleService,
                              DisciplineRepository disciplineRepository) {
        this.userService = userService;
        this.scheduleService = scheduleService;
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping({"", "/", "/list"})
    public String viewSchedule(@RequestParam(required = false) String disciplineName,
                               @RequestParam(required = false) String dayOfWeek,
                               Model model) {

        List<Schedule> schedules;
        if (disciplineName != null && !disciplineName.isEmpty()) {
            schedules = scheduleService.findByDisciplineName(disciplineName);
        } else if (dayOfWeek != null && !dayOfWeek.isEmpty()) {
            schedules = scheduleService.findByDayOfWeek(dayOfWeek);
        } else {
            schedules = scheduleService.getAllSchedules();
        }

        model.addAttribute("schedules", schedules);
        model.addAttribute("disciplineNames", scheduleService.getAllDisciplineNames());
        model.addAttribute("daysOfWeek", scheduleService.getAllDaysOfWeek());
        model.addAttribute("disciplines", disciplineRepository.findAllByOrderByNameAsc());

        return "schedule";
    }

    @GetMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("disciplines", disciplineRepository.findAllByOrderByNameAsc());
        model.addAttribute("days", scheduleService.getAllDaysOfWeek());
        return "admin/addSchedule";
    }

    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addSchedule(@ModelAttribute Schedule schedule,
                              @RequestParam Long disciplineId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid discipline Id: " + disciplineId));
        schedule.setDiscipline(discipline);
        userService.saveSchedule(schedule);
        return "redirect:/schedule";
    }

    @GetMapping("/admin/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("disciplines", disciplineRepository.findAllByOrderByNameAsc());
        model.addAttribute("days", scheduleService.getAllDaysOfWeek());
        return "admin/editSchedule";
    }

    @PostMapping("/admin/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateSchedule(@PathVariable Long id,
                                 @ModelAttribute Schedule schedule,
                                 @RequestParam Long disciplineId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid discipline Id: " + disciplineId));
        schedule.setId(id);
        schedule.setDiscipline(discipline);
        userService.saveSchedule(schedule);
        return "redirect:/schedule";
    }

    @GetMapping("/admin/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSchedule(@PathVariable Long id) {
        userService.deleteSchedule(id);
        return "redirect:/schedule";
    }
}