package org.skypro.coursework.service;

import org.jetbrains.annotations.NotNull;
import org.skypro.coursework.exception.ExceededCountQuestionsException;
import org.skypro.coursework.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getJavaQuestions(int amount) {
        return getQuestions(amount, javaQuestionService);
    }

    @Override
    public Collection<Question> getMathQuestions(int amount) {
        return getQuestions(amount, mathQuestionService);
    }

    @NotNull
    private Collection<Question> getQuestions(int amount, QuestionService javaQuestionService) {
        if (amount > javaQuestionService.getAll().size()) {
            throw new ExceededCountQuestionsException();
        }
        Collection<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(javaQuestionService.getRandomQuestion());
        }
        return questions;
    }
}
