package org.skypro.coursework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework.exception.QuestionNotFoundException;
import org.skypro.coursework.model.Question;
import org.skypro.coursework.repository.MathQuestionRepository;
import org.skypro.coursework.repository.QuestionRepository;
import org.skypro.coursework.service.MathQuestionService;


import java.util.*;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    private Random random;
    private MathQuestionService service;

    @BeforeEach
    public void setUp() {
        random = mock(Random.class);
        QuestionRepository mathQuestionRepository = new MathQuestionRepository();
        service = new MathQuestionService(random, mathQuestionRepository);
    }

    //Тест корректрого добавления вопроса. При добавлении нового вопроса
    // а) сервис возвращает объект Question с вопросом;
    // б) вопрос добавляется в хранилище.
    @Test
    void addQuestion() {
        Question question = new Question("Test", "Test");
        Set<Question> questions = new HashSet<>();
        questions.add(question);

        Assertions.assertEquals(question, service.add("Test", "Test"));
        Assertions.assertEquals(questions, service.getAll());
    }

    //Тест корректного удаления существующего вопроса.
    // а) сервис возвращает объект Question с вопросом;
    // б) вопрос удаляется из хранилища.
    @Test
    void removeExistingQuestion() {
        Question question = new Question("Test", "Test");
        service.add(question);

        Assertions.assertEquals(question, service.remove(question));
        Assertions.assertEquals(Collections.emptySet(), service.getAll());
    }

    //Тест удаления несуществующего вопроса.
    //При попытке удалить несуществующий вопрос и при отсутствии вопросов выбрасывается QuestionNotFoundException.
    @Test
    void removeNonExistingQuestion() {
        Question question1 = new Question("Test", "Test");
        Question question2 = new Question("Test1", "Test1");
        service.add(question1);

        Assertions.assertThrows(QuestionNotFoundException.class, () -> service.remove(question2));
    }

    //Тест получения рандомного вопроса
    @Test
    void getRandomQuestion() {
        Question question = new Question("Test", "Test");
        Question question1 = new Question("Test1", "Test1");
        Question question2 = new Question("Test2", "Test2");
        service.add(question);
        service.add(question1);
        service.add(question2);

        Mockito.when(random.nextInt(3)).thenReturn(0, 1, 2);

        Iterator<Question> iterator = service.getAll().iterator();

        for (int i = 0; i < 3; i++) {
            Question question3 = iterator.next();
            Assertions.assertEquals(question3, service.getRandomQuestion());
        }
        //Работоспособность метода getAll() уже проверяется в имеющихся тестах и отдельной проверки не требуется.
    }
}
