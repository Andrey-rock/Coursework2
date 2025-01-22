package org.skypro.coursework.repository;

import jakarta.annotation.PostConstruct;
import org.skypro.coursework.exception.QuestionNotFoundException;
import org.skypro.coursework.model.Question;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    Set<Question> questionsMath = new HashSet<>();


    @Override
    public Question add(String question, String answer) {

        Question q = new Question(question, answer);
        questionsMath.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questionsMath.add(question);
        return question;
    }


    @Override
    public Question remove(Question question) {
        if (questionsMath.contains(question)) {
            questionsMath.remove(question);
        } else {
            throw new QuestionNotFoundException();
        }
        return question;
    }


    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questionsMath);
    }

    @PostConstruct
    public void init() {
        Question question1 = new Question("Дважды два",
                "четыре");
        Question question2 = new Question("Как называется график функции 1/x ?",
                "гипербола");
        Question question3 = new Question("Что такое логарифм ?",
                "Логарифм числа  a по основанию  b (где b > 0, a > 0, a != 0) — это показатель степени, " +
                        "в которую нужно возвести число a, чтобы получилось число b.");
        questionsMath.addAll(Arrays.asList(question1, question2, question3));
    }
}
