package ru.swmi.quiz.application.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class QuestionsParserCSVTest extends QuizAbstractTest {
    @InjectMocks
    QuestionsParserCSV questionsParser;

    @DisplayName("Test for CSV parsing")
    @Test
    void shouldParseQuestions() {
        assertThat(questionsParser.parseQuestions(source)).isEqualTo(questions);
    }
}