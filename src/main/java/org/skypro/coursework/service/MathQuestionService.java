package org.skypro.coursework.service;

import org.skypro.coursework.exception.MathCrudException;
import org.skypro.coursework.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService {

    private final Random random;

    public MathQuestionService(Random random) {
        this.random = random;
    }


    @Override
    public Question add(String question, String answer) {
        throw new MathCrudException();
    }

    @Override
    public Question add(Question question) {
        throw new MathCrudException();
    }

    @Override
    public Question remove(Question question) {
        throw new MathCrudException();
    }

    @Override
    public Collection<Question> getAll() {
        throw new MathCrudException();
    }

    @Override
    public Question getRandomQuestion() {

        Collection<Question> questionSet = initialMathQuestionSet();
        return getQuestion(questionSet, random);
    }

    public Set<Question> initialMathQuestionSet() {
        Question question1 = new Question("Дважды два",
                "четыре");
        Question question2 = new Question("Как называется график функции 1/x ?",
                "гипербола");
        Question question3 = new Question("Что такое логарифм ?",
                "Логарифм числа  a по основанию  b (где b больше 0, a больше 0, a не равно 0) — это " +
                        "показатель степени, в которую нужно возвести число a, чтобы получилось число b.");

        return new HashSet<>(Arrays.asList(question1, question2, question3));
    }
}
