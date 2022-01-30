package ru.swmi.quiz.application.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test for questions resource loading")
@SpringBootTest(classes = ContentLoaderResource.class)
class ContentLoaderResourceTest {

    @Autowired
    private ContentLoader contentLoader;

    @Test
    void readResource() {
        String expected = "Text of Question 1?,1,Yes,No\r\n" +
                "Text of Question 2?,3,Yes,No,May be\r\n" +
                "Text of Question 3?,1,Yes,No\r\n" +
                "Text of Question 4?,1,Yes,No\r\n" +
                "Text of Question 5?,1,Yes,No";
        assertThat(contentLoader.readResource("questions/questions.csv")).isEqualTo(expected);
    }
}