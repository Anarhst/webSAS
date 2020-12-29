package ru.mirea.intro.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.intro.exception.NoSuchRequest;
import ru.mirea.intro.service.TestService;
import ru.mirea.intro.service.model.Book;
import ru.mirea.intro.service.model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestServiceImplTest {
    @Autowired
    TestService testService;

    @DisplayName("Testing for NoSuchRequest")
    @Test
    void testServiceGetMethodException() {
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServiceGetMethod(1234L)
        );
    }

    @DisplayName("Testing for normal response")
    @Test
    @Transactional
    void testServiceGetMethod() throws NoSuchRequest {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(16L, "Толстой", "Война и мир"));
        Request request = new Request(252L, "Первый запрос", bookList);
        Assertions.assertEquals(request, testService.testServiceGetMethod(252L));
    }


    @DisplayName("Testing for normal post")
    @Test
    @Transactional
    void testServicePostMethod() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(456L, "Толстой Тест 123", "Война и Мир Тест 123"));
        Request request = new Request(new Random().nextLong(), "Второй запрос из теста", bookList);
        Assertions.assertEquals("Successfully inserted row!", testService.testServicePostMethod(request));
    }

    @DisplayName("Testing for NoSuchRequest")
    @Test
    void testServicePutMethodException() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(123L,"adasdasdasds","asdasdadasdsddd"));
        Request request = new Request(1234L, "Худой Тест 123", bookList);
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServicePutMethod(request));
    }

    @DisplayName("Testing for normal put")
    @Test
    @Transactional
    void testServicePutMethod() throws NoSuchRequest {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(456L, "Толстой Тест 123", "Война и Мир Тест 123"));
        Request request = new Request(2L,"Int",bookList);
        Assertions.assertEquals("Successfully updated row!", testService.testServicePutMethod(request));
    }


    @DisplayName("Testing for NoSuchRequest")
    @Test
    void testServiceDeleteMethodException() {
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServiceDeleteMethod(99999L));
    }

    @DisplayName("Testing for normal delete")
    @Test
    @Transactional
    void testServiceDeleteMethod() throws NoSuchRequest {
        testService.testServiceDeleteMethod(2L);
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServiceGetMethod(2L));
    }
}