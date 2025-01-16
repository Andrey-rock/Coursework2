package org.skypro.coursework.service;

import jakarta.annotation.PostConstruct;
import org.skypro.coursework.exception.NoQuestionsInServiceException;
import org.skypro.coursework.exception.QuestionNotFoundException;
import org.skypro.coursework.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Random random;

    Set<Question> questions = new HashSet<>();

    public JavaQuestionService(Random random) {
        this.random = random;
    }

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
        } else {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NoQuestionsInServiceException();
        }
        int number = random.nextInt(questions.size());
        int i = 0;
        for (Question q : questions) {
            if (i == number) {
                return q;
            }
            i++;
        }
        return null;
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
        questions.addAll(Arrays.asList(question1, question2, question3));
    }
}
