package com.online.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.online.exam.entity.Student;
import com.online.exam.entity.StudentResponse;
import com.online.exam.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Student student) {
        studentService.register(student);
        return "redirect:/student/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Student student, Model model) {
        Student logged = studentService.login(student.getEmail(), student.getPassword());
        if (logged != null) {
            model.addAttribute("student", logged);
            model.addAttribute("exams", studentService.getAssignedExams(logged.getId()));
            return "student/dashboard";
        } else {
            return "redirect:/student/login?error";
        }
    }

    @GetMapping("/take-exam/{examId}")
    public String takeExam(@PathVariable Long examId, Model model) {
        model.addAttribute("questions", studentService.getExamQuestions(examId));
        return "student/take-exam";
    }

    @PostMapping("/submit-exam")
    public String submitExam(@ModelAttribute("responses") List<StudentResponse> responses,
                              @RequestParam Long studentId,
                              @RequestParam Long examId) {
        studentService.saveResponses(studentId, examId, responses);
        return "redirect:/student/login";
    }
}
