package org.skypro.coursework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework.exception.MathCrudException;
import org.skypro.coursework.model.Question;
import org.skypro.coursework.service.MathQuestionService;


import java.util.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    @Mock
    private Random random;

    @InjectMocks
    private MathQuestionService service;


    //Тест добавления вопроса.
    @Test
    void addQuestion() {

        Assertions.assertThrows(MathCrudException.class, () -> service.add("Test", "Test"));
    }

    //Тест удаления вопроса.

    @Test
    void removeExistingQuestion() {
        Question question = new Question("Test", "Test");

        Assertions.assertThrows(MathCrudException.class, () -> service.remove(question));
    }

    //Тест получения рандомного вопроса
    @Test
    void getRandomQuestion() {

        Mockito.when(random.nextInt(3)).thenReturn(0, 1, 2);
        Set<Question> questions = service.initialMathQuestionSet();

        Iterator<Question> iterator = questions.iterator();

        for (int i = 0; i < 3; i++) {
            Question question3 = iterator.next();
            Assertions.assertEquals(question3, service.getRandomQuestion());
        }
        //Работоспособность метода getAll() уже проверяется в имеющихся тестах и отдельной проверки не требуется.
    }
}
