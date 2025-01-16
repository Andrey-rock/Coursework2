package org.skypro.coursework.controller;

import org.jetbrains.annotations.NotNull;
import org.skypro.coursework.exception.ExceededCountQuestionsException;
import org.skypro.coursework.exception.NoQuestionsInServiceException;
import org.skypro.coursework.exception.QuestionNotFoundException;
import org.skypro.coursework.model.ExamError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExamControllerAdvice {

    @ExceptionHandler(ExceededCountQuestionsException.class)
    public ResponseEntity<ExamError> handleExceededCountQuestionsException(@NotNull ExceededCountQuestionsException e) {
        ExamError examError = new ExamError("400", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examError);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ExamError> handleIllegalArgumentException(@NotNull QuestionNotFoundException e) {
        ExamError examError = new ExamError("400", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examError);
    }

    @ExceptionHandler(NoQuestionsInServiceException.class)
    public ResponseEntity<ExamError> handleIllegalArgumentException(@NotNull NoQuestionsInServiceException e) {
        ExamError examError = new ExamError("404", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(examError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExamError> handleIllegalArgumentException(@NotNull MethodArgumentTypeMismatchException e) {
        ExamError examError = new ExamError("400", "Неверный запрос");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examError);
    }
}
