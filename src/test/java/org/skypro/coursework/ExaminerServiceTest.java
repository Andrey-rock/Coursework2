package org.skypro.coursework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework.exception.ExceededCountQuestionsException;
import org.skypro.coursework.repository.JavaQuestionRepository;
import org.skypro.coursework.repository.QuestionRepository;
import org.skypro.coursework.service.ExaminerServiceImpl;
import org.skypro.coursework.service.JavaQuestionService;
import org.skypro.coursework.service.MathQuestionService;
import org.skypro.coursework.service.QuestionService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    private void initializationQuestionService() {
        javaQuestionService.add("test1", "test1");
        javaQuestionService.add("test2", "test2");
        javaQuestionService.add("test3", "test3");
    }

    //В данном случае я решил проверить всю функциональность с реальными классами,
    // т.к. имитировать заранее извесные значения не требуется, а тесты от этого, я думаю, выиграют по всем параметрам:
    //будут лучше отлавливать баги и одновременно станут менее хрупкими.

    private final Random random = new Random();
    private final QuestionRepository javaQuestionRepository = new JavaQuestionRepository();

    private final JavaQuestionService javaQuestionService = new JavaQuestionService(random, javaQuestionRepository);
    private final MathQuestionService mathQuestionService = new MathQuestionService(random);
    private final List<QuestionService> questionServiceList = new ArrayList<>(Arrays.asList(javaQuestionService, mathQuestionService));
    private final ExaminerServiceImpl examinerService = new ExaminerServiceImpl(questionServiceList);

    //Если пользователь запрашивает 0 вопросов, то получает пустую коллекцию
    //Возможно с точки зрения теориии тестирования тест не очень полезен, т.к. проверяет слишком простую функциональность,
    //но сценарий возможен гипотетически.
    @Test
    void IfRequestedZeroQuestions() {
        Assertions.assertEquals(Collections.emptySet(), examinerService.getJavaQuestions(0));
        Assertions.assertEquals(Collections.emptySet(), examinerService.getMathQuestions(0));
    }

    //Если пользователь запрашивает количество вопросов n > 0, но <= количества вопросов в сервисе,
    //то получает набор из n случайных вопросов.
    @Test
    void IfRequestedSeveralQuestions() {

        initializationQuestionService();

        Assertions.assertEquals(1, examinerService.getJavaQuestions(1).size());
        Assertions.assertEquals(2, examinerService.getJavaQuestions(2).size());
        Assertions.assertEquals(3, examinerService.getJavaQuestions(3).size());
        Assertions.assertEquals(1, examinerService.getMathQuestions(1).size());
        Assertions.assertEquals(2, examinerService.getMathQuestions(2).size());
        Assertions.assertEquals(3, examinerService.getMathQuestions(3).size());
    }

    //Если пользователь запрашивает больше вопросов чем количества вопросов в сервисе,
    //то получает ошибку ExceededCountQuestionsException со статусом BAD_REQUEST.
    @Test
    void IfRequestedMoreQuestionThenExist() {

        initializationQuestionService();

        Assertions.assertThrows(ExceededCountQuestionsException.class, () -> examinerService.getJavaQuestions(4));
        Assertions.assertThrows(ExceededCountQuestionsException.class, () -> examinerService.getMathQuestions(4));
    }
}
