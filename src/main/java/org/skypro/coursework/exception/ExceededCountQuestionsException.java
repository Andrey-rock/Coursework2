package org.skypro.coursework.exception;

public class ExceededCountQuestionsException extends RuntimeException {
    public ExceededCountQuestionsException() {
        super("Запрошено количесво вопросов больше максимального");
    }
}
