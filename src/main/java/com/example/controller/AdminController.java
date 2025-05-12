package com.example.controller;

import com.example.model.UserDtls;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    // Страница пользователей для администратора с фильтрацией по роли
    @GetMapping("/admin/users")
    public String viewUsers(@RequestParam(value = "role", defaultValue = "") String role, Model model) {
        List<UserDtls> users = userService.getAllUsers(role);
        model.addAttribute("users", users);
        return "admin/users"; // Открывает шаблон users.html в папке admin
    }

    // Страница редактирования пользователя
    @GetMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        UserDtls user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/editUser"; // Открывает шаблон редактирования пользователя
    }

    // Обработка редактирования пользователя
    @PostMapping("/admin/users/edit/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @ModelAttribute("user") UserDtls user,
                             @RequestParam(value = "password", required = false) String password,
                             Model model) {

        // Проверка: если email уже занят другим пользователем
        UserDtls existingByEmail = userService.getUserByEmail(user.getEmail());
        if (existingByEmail != null && !existingByEmail.getId().equals(id)) {
            model.addAttribute("user", user); // сохранить заполненные данные в форме
            model.addAttribute("emailError", "Этот email уже используется другим пользователем.");
            return "admin/editUser";
        }

        user.setId(id);

        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        } else {
            UserDtls existingUser = userService.getUserById(id);
            user.setPassword(existingUser.getPassword());
        }

        userService.saveUser(user);
        return "redirect:/admin/users";
    }


    // Удаление пользователя
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users"; // Перенаправление обратно на страницу пользователей после удаления
    }

    // Главная страница администратора
    @GetMapping("/admin/index1")
    public String adminHome() {
        return "admin/index1";
    }

}

