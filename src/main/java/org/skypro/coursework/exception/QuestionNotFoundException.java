package org.skypro.coursework.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
        super("Вопрос не найден");
    }
}
