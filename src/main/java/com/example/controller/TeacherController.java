package com.example.controller;

import com.example.model.TeacherRegistration;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-to-teacher")
    public String registerToTeacher(TeacherRegistration registration) {
        userService.saveTeacherRegistration(registration);
        return "redirect:/teachers?success";
    }

    @GetMapping("/admin/history")
    public String showAdminHistory(Model model) {
        model.addAttribute("registrations", userService.getAllRegistrations());
        return "admin/history";
    }


    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TeacherRegistration registration = userService.getRegistrationById(id);
        model.addAttribute("registration", registration);
        return "admin/edit-registration"; // должен быть шаблон с этой формой
    }


    @PostMapping("/admin/update")
    public String updateRegistration(@ModelAttribute TeacherRegistration registration) {
        userService.saveTeacherRegistration(registration);
        return "redirect:/admin/history";
    }


    @PostMapping("/admin/delete/{id}")
    public String deleteRegistration(@PathVariable Long id) {
        userService.deleteRegistrationById(id);
        return "redirect:/admin/history";
    }
}
