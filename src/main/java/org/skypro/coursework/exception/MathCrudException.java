package org.skypro.coursework.exception;

public class MathCrudException extends RuntimeException {
    public MathCrudException() {
        super("Сервер не поддерживает этот метод");
    }
}
