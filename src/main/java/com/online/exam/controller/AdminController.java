package com.online.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.online.exam.entity.Admin;
import com.online.exam.entity.Exam;
import com.online.exam.service.AdminService;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
   

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Admin admin) {
        adminService.register(admin);
        return "redirect:/admin/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin, Model model) {
        Admin logged = adminService.login(admin.getEmail(), admin.getPassword());
        if (logged != null) {
            model.addAttribute("admin", logged);
            model.addAttribute("exams", adminService.getAllExams());
            model.addAttribute("students", adminService.getAllStudents());
            model.addAttribute("exam", new Exam()); //add for rendering issue
 
            return "admin/dashboard";
        } else {
            return "redirect:/admin/login?error";
        }
    }

    @PostMapping("/create-exam")
    public String createExam(@ModelAttribute Exam exam, Model model) {
        adminService.createExam(exam);
        return "redirect:/admin/login";
    }

    @PostMapping("/assign-exam")
    public String assignExam(@RequestParam Long examId, @RequestParam Long studentId) {
        adminService.assignExamToStudent(examId, studentId);
        return "redirect:/admin/login";
    }
}