package com.nopointer.server.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.TestModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestModule());
        controller = injector.getInstance(Controller.class);
    }

    @Test
    public void registerUser() throws Exception {
        assertEquals(controller.registerUser("Oleg", "pass"), 200);
        assertEquals(controller.registerUser("Kirill", "pass"), 100);
    }

    @Test
    public void deleteUser() throws Exception {
        controller.deleteUser("Oleg");
        assertEquals("a", "a");
    }

    @Test
    public void login() throws Exception {
        assertEquals(controller.login("oleg", "ovcharuk"), 100);
        assertEquals(controller.login("oleg2", "ovcharuk"), 200);
    }
//
//    @Test
//    public void createFile() throws Exception {
//
//    }
//
//    @Test
//    public void getTitle() throws Exception {
//
//    }
//
//    @Test
//    public void getFileStatus() throws Exception {
//
//    }
//
//    @Test
//    public void changeFileStatus() throws Exception {
//
//    }
//
//    @Test
//    public void getAllFilesId() throws Exception {
//
//    }
//
//    @Test
//    public void getActualText() throws Exception {
//
//    }

}