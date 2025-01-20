package org.skypro.coursework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework.exception.ExceededCountQuestionsException;
import org.skypro.coursework.service.ExaminerServiceImpl;
import org.skypro.coursework.service.JavaQuestionService;

import java.util.Collections;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    private void initializationJavaQuestionService() {
        questionService.add("test1", "test1");
        questionService.add("test2", "test2");
        questionService.add("test3", "test3");
    }

    //В данном случае я решил проверить всю функциональность с реальными классами,
    // т.к. имитировать заранее извесные значения не требуется, а тесты от этого, я думаю, выиграют по всем параметрам:
    //будут лучше отлавливать баги и одновременно станут менее хрупкими.

    private final Random random = new Random();
    private final JavaQuestionService questionService = new JavaQuestionService(random);
    private final ExaminerServiceImpl examinerService = new ExaminerServiceImpl(questionService);

    //Если пользователь запрашивает 0 вопросов, то получает пустую коллекцию
    //Возможно с точки зрения теориии тестирования тест не очень полезен, т.к. проверяет слишком простую функциональность,
    //но сценарий возможен гипотетически.
    @Test
    void IfRequestedZeroQuestions() {
        Assertions.assertEquals(Collections.emptySet(), examinerService.getQuestions(0));
    }

    //Если пользователь запрашивает количество вопросов n > 0, но < количества вопросов в сервисе,
    //то получает набор из n случайных вопросов.
    @Test
    void IfRequestedSeveralQuestions() {

        initializationJavaQuestionService();

        Assertions.assertEquals(1, examinerService.getQuestions(1).size());
        Assertions.assertEquals(2, examinerService.getQuestions(2).size());
        Assertions.assertEquals(3, examinerService.getQuestions(3).size());
    }

    //Если пользователь запрашивает больше вопросов чем количества вопросов в сервисе,
    //то получает ошибку ExceededCountQuestionsException со статусом BAD_REQUEST.
    @Test
    void IfRequestedMoreQuestionThenExist() {

        initializationJavaQuestionService();

        Assertions.assertThrows(ExceededCountQuestionsException.class, () -> examinerService.getQuestions(4));
    }
}
