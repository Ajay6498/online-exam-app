package com.online.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.exam.entity.Admin;
import com.online.exam.entity.Exam;
import com.online.exam.entity.Student;
import com.online.exam.repository.AdminRepository;
import com.online.exam.repository.ExamRepository;
import com.online.exam.repository.StudentRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Admin register(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin login(String email, String password) {
        return adminRepository.findByEmail(email)
            .filter(a -> a.getPassword().equals(password))
            .orElse(null);
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void assignExamToStudent(Long examId, Long studentId) {
        Exam exam = examRepository.findById(examId).orElseThrow();
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.getAssignedExams().add(exam);
        studentRepository.save(student);
    }
}
