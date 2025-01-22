package org.skypro.coursework.repository;

import org.skypro.coursework.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
