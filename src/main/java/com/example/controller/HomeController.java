package com.example.controller;

import com.example.service.UserService;
import com.example.model.UserDtls;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/loginSuccess")
    public String loginSuccess(HttpSession session) {
        UserDtls loggedUser = (UserDtls) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            if ("ADMIN".equals(loggedUser.getRole())) {
                return "redirect:/admin/users";
            } else {
                return "redirect:/user/home";
            }
        }
        return "redirect:/signin";
    }


    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";
    }

    @GetMapping("/aboutschool")
    public String aboutschool() {
        return "aboutschool";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin/home";
    }

    @RequestMapping("/register")
    public String registerPage(HttpSession session) {
// Очистка старых сообщений перед загрузкой страницы
        session.removeAttribute("errorMsg");
        session.removeAttribute("succMsg");
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user,
                           @RequestParam("img") MultipartFile file,
                           @RequestParam("role") String role,
                           @RequestParam("activity") String activity, // Получаем активность
                           HttpSession session) throws IOException {

// Проверка на существующий email
        Boolean existsEmail = userService.existsEmail(user.getEmail());

        if (existsEmail) {
            session.setAttribute("errorMsg", "Email already exists");
        } else {
// Установка роли
            if ("ADMIN".equals(role)) {
                user.setRole("ADMIN");
            } else {
                user.setRole("USER");
            }

// Установка деятельности
            user.setActivity(activity); // Сохраняем выбранную деятельность

// Обработка изображения
            String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
            user.setProfileImage(imageName);

// Сохранение пользователя
            UserDtls savedUser = userService.saveUser(user);

            if (!ObjectUtils.isEmpty(savedUser)) {
// Сохранение изображения
                if (!file.isEmpty()) {
                    File saveDir = new File("src/main/resources/static/img/profile_img");
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }
                    Path path = Paths.get(saveDir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }

                session.setAttribute("succMsg", "Registration successful");
            } else {
                session.setAttribute("errorMsg", "Something went wrong on server");
            }
        }

// После регистрации редирект на страницу входа
        return "redirect:/signin";
    }


}