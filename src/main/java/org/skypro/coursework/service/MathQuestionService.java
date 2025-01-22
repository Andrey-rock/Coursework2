package org.skypro.coursework.service;

import org.skypro.coursework.model.Question;
import org.skypro.coursework.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService {

    private final Random random;
    private final QuestionRepository questionRepository;

    public MathQuestionService(Random random, @Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        this.random = random;
        this.questionRepository = questionRepository;
    }


    @Override
    public Question add(String question, String answer) {
        return questionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        return getQuestion(questionRepository.getAll(), random);
    }
}
