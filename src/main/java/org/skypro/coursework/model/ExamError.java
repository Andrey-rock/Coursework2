package org.skypro.coursework.model;

public class ExamError {
    private final String code;
    private final String message;

    public ExamError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
