package org.solo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.solo.dto.*;
import org.solo.exceptions.InvalidUsernameOrPasswordException;
import org.solo.exceptions.UserExistException;
import org.solo.models.TaskStatus;
import org.solo.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private Users users;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private TaskRequest taskRequest;
    private StartTaskRequest startTaskRequest;
    private MarkTaskRequest markTaskRequest;
    @BeforeEach
    public void setUp(){
        users.deleteAll();
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Solomon");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        taskRequest = new TaskRequest();
        taskRequest.setUsername("username");
        taskRequest.setTitle("title");

        startTaskRequest = new StartTaskRequest();
        startTaskRequest.setUsername("username");
        startTaskRequest.setId("id");

        markTaskRequest = new MarkTaskRequest();
        markTaskRequest.setUsername("username");
        markTaskRequest.setId("id");



    }

    @Test
    public void testUserCanRegister(){
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Solomon");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userService.register(registerRequest);
        assertThat(users.count(), is(1L));
    }

    @Test
    public void testUserCannotRegisterTwice(){
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Solomon");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userService.register(registerRequest);
        try {
            userService.register(registerRequest);
        } catch (UserExistException e) {
            assertThat(e.getMessage(), containsString("username already exists"));
        }
        assertThat(users.count(), is(1L));

    }

    @Test
    public void testUserCanLoginWithCorrectUserNameAndPassword(){
        userService.register(registerRequest);
        assertThat(users.count(), is(1L));
        var loginResponse = userService.login(loginRequest);
        assertThat(loginResponse.getId(), notNullValue());
    }
    @Test
    public void loginWithIncorrectPassword_throwsExceptionTest(){
        userService.register(registerRequest);
        loginRequest.setPassword("incorrectPassword");
        try {
            userService.login(loginRequest);
        } catch (InvalidUsernameOrPasswordException e) {
            assertThat(e.getMessage(), containsString("Invalid Username or password"));
        }
    }

    @Test
    public void testUserCanCreateANewTask(){
        userService.register(registerRequest);
        assertThat(users.count(), is(1L));
        userService.login(loginRequest);
        var checkUser = users.findByUsername(registerRequest.getUsername());
        assertThat(checkUser.getTasks().size(), is(0));
        taskRequest.setUsername("username");
        taskRequest.setTitle("title");
        var taskResponse = userService.createTask(taskRequest);
        var foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        assertThat(foundUser.getTasks().size(), is(1));
        assertThat(taskResponse.getId(), notNullValue());

    }

    @Test
    public void testUserCanStartTask(){
        userService.register(registerRequest);
        userService.login(loginRequest);
        taskRequest.setUsername("username");
        taskRequest.setTitle("title");
        var taskResponse = userService.createTask(taskRequest);
        String taskId = taskResponse.getId();
        startTaskRequest.setUsername(registerRequest.getUsername());
        startTaskRequest.setId(taskId);
        userService.startTask(startTaskRequest);
        var startedTask = taskService.findTaskById(taskId);
        assertThat(startedTask.getStatus(), is(TaskStatus.IN_PROGRESS));
        assertThat(startedTask.getStartTime(), notNullValue());
    }
    @Test
    public void testUserMarkTaskAsCompleted(){
        userService.register(registerRequest);
        userService.login(loginRequest);
        taskRequest.setUsername("username");
        taskRequest.setTitle("title");
        var taskResponse = userService.createTask(taskRequest);
        String taskId = taskResponse.getId();
        startTaskRequest.setUsername(registerRequest.getUsername());
        startTaskRequest.setId(taskId);
        userService.startTask(startTaskRequest);
        var startedTask = taskService.findTaskById(taskId);
        assertThat(startedTask.getStatus(), is(TaskStatus.IN_PROGRESS));
        assertThat(startedTask.getStartTime(), notNullValue());
        userService.markTaskAsCompleted(markTaskRequest);
        var completedTask = taskService.findTaskById(taskId);
        assertThat(completedTask.getStatus(), is(TaskStatus.COMPLETE));
        assertThat(completedTask.getEndTime(), notNullValue());

    }


    }
