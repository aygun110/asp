package com.example.controller;

import com.example.service.UserService;
import com.example.model.UserDtls;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/logins")
    public String logins() {
        return "logins";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
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


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session)
            throws IOException {

        Boolean existsEmail = userService.existsEmail(user.getEmail());

        if (existsEmail) {
            session.setAttribute("errorMsg", "Email already exists");
        } else {
            String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
            user.setProfileImage(imageName);
            UserDtls saveUser = userService.saveUser(user);

            if (!ObjectUtils.isEmpty(saveUser)) {
                if (!file.isEmpty()) {
                    // Путь для сохранения изображения
                    File saveDir = new File("src/main/resources/static/img/profile_img");

                    // Создаем директорию, если её нет
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }

                    // Путь к файлу
                    Path path = Paths.get(saveDir.getAbsolutePath() + File.separator + file.getOriginalFilename());

                    // Копируем файл в указанную директорию
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }
                session.setAttribute("succMsg", "Registration successful");
            } else {
                session.setAttribute("errorMsg", "Something went wrong on server");
            }
        }
        return "redirect:/register";
    }

}


