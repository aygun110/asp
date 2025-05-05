
package com.example.controller;

import com.example.model.TeacherRegistration;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
}



