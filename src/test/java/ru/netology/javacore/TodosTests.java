package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

class TodosTests {

    static Thread thread;
    static Todos todos;

    @BeforeAll
    static void start() {
        thread = new Thread(TodosTests::setUp);
        thread.start();
    }

    @AfterAll
    static void finish() {
        thread.interrupt();
    }

    private static void setUp() {
        todos = new Todos();
        TodoServer server = new TodoServer(8989, todos);
        server.start();
    }

    @Test
    void addTask() {
        todos.addTask("Купить кабель");
        String actual = todos.getAllTasks();
        String expected = "Купить кабель";
        Assertions.assertEquals(expected, actual);
        todos.setListTasks(new ArrayList<>());
    }

    @Test
    void getAllTasks() {
        todos.addTask("Купить кабель");
        todos.addTask("Помыть авто");
        todos.addTask("Выкинуть мусор");
        String actual = todos.getAllTasks();
        String expected = "Выкинуть мусор Купить кабель Помыть авто";
        Assertions.assertEquals(expected, actual);
        todos.setListTasks(new ArrayList<>());
    }

    @Test
    void removeTask() {
        todos.addTask("Купить кабель");
        todos.addTask("Помыть авто");
        todos.removeTask("Помыть авто");
        String actual = todos.getAllTasks();
        String expected = "Купить кабель";
        Assertions.assertEquals(expected, actual);
        todos.setListTasks(new ArrayList<>());
    }

}