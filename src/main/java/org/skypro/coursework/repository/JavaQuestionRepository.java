package org.skypro.coursework.repository;

import jakarta.annotation.PostConstruct;
import org.skypro.coursework.exception.QuestionNotFoundException;
import org.skypro.coursework.model.Question;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JavaQuestionRepository implements QuestionRepository {


    Set<Question> questionsJava = new HashSet<>();


    @Override
    public Question add(String question, String answer) {

        Question q = new Question(question, answer);
        questionsJava.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questionsJava.add(question);
        return question;
    }


    @Override
    public Question remove(Question question) {
        if (questionsJava.contains(question)) {
            questionsJava.remove(question);
        } else {
            throw new QuestionNotFoundException();
        }
        return question;
    }


    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questionsJava);
    }

    @PostConstruct
    public void init() {
        Question question1 = new Question("Назовите целочисленные типы в Java",
                "byte, short, int, long");
        Question question2 = new Question("Назовите интерфейс от которого наследуются коллекции в Java",
                "Iterable");
        Question question3 = new Question("Что такое Java Development Kit",
                "JDK — это комплект для разработки программного обеспечения, " +
                        "который включает инструменты и библиотеки, необходимые для разработки приложений Java");
        questionsJava.addAll(Arrays.asList(question1, question2, question3));

    }
}
