package ru.swmi.quiz.application.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuestionsDaoImplTest extends QuizAbstractTest {
    @InjectMocks
    private QuestionsDaoImpl questionsDao;
    @Mock
    private QuestionsParserCSV questionsParser;
    @Mock
    private ContentLoader contentLoader;

    private static final int QUESTIONS_QUANTITY = 3;

    @DisplayName("Возвращает заданное количество вопросов")
    @Test
    void testGetQuestionsRequiredAmount() {
        given(questionsParser.parseQuestions(any()))
                .willReturn(questions);
        given(contentLoader.readResource(any()))
                .willReturn(source);

        assertThat(questionsDao.getQuestions(any(), QUESTIONS_QUANTITY)).hasSize(QUESTIONS_QUANTITY);
    }
}