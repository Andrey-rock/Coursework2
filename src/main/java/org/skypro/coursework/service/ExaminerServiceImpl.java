package org.skypro.coursework.service;

import org.skypro.coursework.exception.ExceededCountQuestionsException;
import org.skypro.coursework.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServices;

    public ExaminerServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    @Override
    public Collection<Question> getJavaQuestions(int amount) {
        if (amount > questionServices.get(0).getAll().size()) {
            throw new ExceededCountQuestionsException();
        }
        Collection<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(questionServices.get(0).getRandomQuestion());
        }
        return questions;
    }

    @Override
    public Collection<Question> getMathQuestions(int amount) {
        if (amount > 3) {
            throw new ExceededCountQuestionsException();
        }
        Collection<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(questionServices.get(1).getRandomQuestion());
        }
        return questions;
    }
}
