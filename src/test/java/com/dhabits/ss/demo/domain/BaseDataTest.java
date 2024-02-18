package com.dhabits.ss.demo.domain;

import com.dhabits.ss.demo.domain.model.RoleDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public abstract class BaseDataTest {
    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public <T> T readJson(String fileName, Class<T> aClass) throws IOException {
        var json = readFile(fileName);
        return OBJECT_MAPPER.readValue(json, aClass);
    }

    public <T> List<T> readJsonList(String fileName, Class<T> listClass) throws IOException {
        var json = readFile(fileName);
        JavaType listType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, listClass);
        return OBJECT_MAPPER.readValue(json, listType);
    }


    public JsonNode readTree(String fileName) throws IOException {
        var json = readFile(fileName);
        return OBJECT_MAPPER.readTree(json);
    }

    private String readFile(String fileName) throws IOException {
        var fullFileName = getResourcePath(fileName);
        ClassPathResource resource = new ClassPathResource(fullFileName);
        List<String> lines = Files.readAllLines(resource.getFile().toPath());
        return StringUtils.join(lines, "");
    }

    private String getResourcePath(String fileName) {
        var walker = StackWalker.getInstance();
        Optional<String> methodName = walker.walk(frames ->
                frames
                        .filter(it -> {
                            try {
                                return Class.forName(it.getClassName())
                                        .getMethod(it.getMethodName())
                                        .getAnnotation(Test.class) != null;
                            } catch (NoSuchMethodException | ClassNotFoundException e) {
                                return false;
                            }
                        })
                        .map(StackWalker.StackFrame::getMethodName)
                        .findFirst()
        );
        var pathToClass = Paths.get(getClass().getName().replace(".", "/"));
        var pathToTestResource = pathToClass.resolve(methodName.orElse(""));

        return pathToTestResource.resolve(fileName).toString();
    }
}
