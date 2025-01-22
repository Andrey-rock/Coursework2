package org.skypro.coursework.service;

import org.skypro.coursework.exception.NoQuestionsInServiceException;
import org.skypro.coursework.model.Question;
import java.util.Collection;
import java.util.Random;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();


    default Question getQuestion(Collection<Question> questions, Random random) {
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
}
