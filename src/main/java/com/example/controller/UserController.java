package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @GetMapping("/user/home")
    public String userHome() {
        return "user/home";
    }

    @PostMapping("/user/register-to-teacher")
    public String registerToTeacher(
            @RequestParam String teacherName,
            @RequestParam String sportType,
            @RequestParam String userName,
            @RequestParam String phone,
            @RequestParam String groupName,
            @RequestParam String schedule,

            RedirectAttributes redirectAttributes
    ) {
        // Здесь сохраняешь в БД:
        // TeacherRegistration reg = new TeacherRegistration(teacherName, sportType, userName, phone, group);
        // registrationRepository.save(reg);

        redirectAttributes.addAttribute("success", "true");
        return "redirect:/teachers"; // укажи актуальный адрес, куда перенаправлять
    }
}

