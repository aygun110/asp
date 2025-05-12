package com.example.controller;

import com.example.service.UserService;
import com.example.model.UserDtls;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "logins";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";
    }


    @GetMapping("/teachers")
    public String teachers() {
        return "teachers";
    }

    @GetMapping("/discipline")
    public String discipline() {
        return "discipline";
    }


    @GetMapping("/me")
    public String me() {
        return "me";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, HttpSession session) {

        Boolean existsEmail = userService.existsEmail(user.getEmail());

        if (existsEmail) {
            session.setAttribute("errorMsg", "Email already exists");
        } else {
            // Если роль "Админ", очищаем поле activity, чтобы оно не сохранялось
            if ("ADMIN".equals(user.getRole())) {
                user.setActivity(null); // Очищаем поле activity
            }

            UserDtls saveUser = userService.saveUser(user);

            if (!ObjectUtils.isEmpty(saveUser)) {
                session.setAttribute("succMsg", "Registration successful");
            } else {
                session.setAttribute("errorMsg", "Something went wrong on server");
            }
        }
        return "redirect:/signin";
    }
}