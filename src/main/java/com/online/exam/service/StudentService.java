package com.online.exam.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.exam.entity.Exam;
import com.online.exam.entity.Question;
import com.online.exam.entity.Student;
import com.online.exam.entity.StudentResponse;
import com.online.exam.repository.ExamRepository;
import com.online.exam.repository.QuestionRepository;
import com.online.exam.repository.StudentRepository;
import com.online.exam.repository.StudentResponseRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentResponseRepository responseRepository;

    public Student register(Student student) {
        return studentRepository.save(student);
    }

    public Student login(String email, String password) {
        return studentRepository.findByEmail(email)
            .filter(s -> s.getPassword().equals(password))
            .orElse(null);
    }

    public List<Exam> getAssignedExams(Long studentId) {
        return studentRepository.findById(studentId)
                .map(Student::getAssignedExams)
                .orElse(Collections.emptyList());
    }

    public List<Question> getExamQuestions(Long examId) {
        return questionRepository.findByExamId(examId);
    }

    public void saveResponses(Long studentId, Long examId, List<StudentResponse> responses) {
        for (StudentResponse response : responses) {
            response.setStudent(new Student());
            response.getStudent().setId(studentId);
            response.setExam(new Exam());
            response.getExam().setId(examId);
        }
        responseRepository.saveAll(responses);
    }
}
