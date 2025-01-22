package org.skypro.coursework.controller;

import org.skypro.coursework.model.Question;
import org.skypro.coursework.service.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/get")
public class ExamController {

    private final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/java/{amount}")
    public Collection<Question> getJavaQuestions(@PathVariable int amount) {
        return examinerService.getJavaQuestions(amount);
    }

    @GetMapping("/math/{amount}")
    public Collection<Question> getMathQuestions(@PathVariable int amount) {
        return examinerService.getMathQuestions(amount);
    }
}
