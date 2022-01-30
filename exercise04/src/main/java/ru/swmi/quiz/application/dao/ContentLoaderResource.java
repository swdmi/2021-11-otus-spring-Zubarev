package ru.swmi.quiz.application.dao;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class ContentLoaderResource implements ContentLoader {

    @Override
    public String readResource(String sourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = Optional.ofNullable(classLoader.getResourceAsStream(sourceName)).orElse(InputStream.nullInputStream())) {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int length; (length = inputStream.read(buffer)) != -1; ) {
                result.write(buffer, 0, length);
            }
            return result.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResourceLoadingException(String.format("Error during reading resource %s", sourceName), e);
        }
    }
}
