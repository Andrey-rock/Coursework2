package org.skypro.coursework.exception;

public class NoQuestionsInServiceException extends RuntimeException {
    public NoQuestionsInServiceException() {
        super("Нет вопросов в базе");
    }
}
