package com.tistory.tazz009.test.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import com.tistory.tazz009.test.webapp.controller.UserController;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;

@RunWith(JMock.class)
public class UserControllerTest {
    UserController c = new UserController();
    UserManager userManager;
    Mockery context = new JUnit4Mockery();

    @Before
    public void setUp() {
        userManager = context.mock(UserManager.class);
        c.userManager = userManager;
    }

    @Test
    public void testGetUsers() {
        // set expected behavior on manager
        User user1 = new User();
        user1.setFirstName("ControllerTest");
        final List<User> users = new ArrayList<User>();
        users.add(user1);

        context.checking(new Expectations() {{
            one(userManager).getUsers();
            will(returnValue(users));
        }});

        ModelMap map = new ModelMap();
        String result = c.execute(map);
        assertFalse(map.isEmpty());
        assertNotNull(map.get("userList"));
        assertEquals("userList", result);
    }
}
