package com.example.controller;
import com.example.model.UserDtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")

public class AdminController {
    @GetMapping("/")
    public String index1() {
        return "admin/index1";
    }

    @GetMapping("/users")
    public String users() {
        return "admin/users";
    }
}


