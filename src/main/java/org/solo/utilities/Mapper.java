package org.solo.utilities;

import org.solo.dto.RegisterRequest;
import org.solo.dto.TaskRequest;
import org.solo.models.Task;
import org.solo.models.TaskStatus;
import org.solo.models.User;
import org.solo.response.LoginResponse;
import org.solo.response.RegisterResponse;
import org.solo.response.StartTaskResponse;
import org.solo.response.TaskResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User registerMap(RegisterRequest registerRequest){
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        return user;
    }
    public static RegisterResponse registerResponseMap(User user){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(user.getId());
        registerResponse.setUsername(user.getUsername());
        registerResponse.setDateRegistered(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(user.getDateRegistered()));
        return registerResponse;
    }
    public static LoginResponse loginResponseMap(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId());
        loginResponse.setUsername(user.getUsername());
        return loginResponse;

    }
    public static TaskResponse taskResponseMap(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setStatus(TaskStatus.PENDING);
        taskResponse.setDateCreated(DateTimeFormatter
               .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(task.getDateCreated()));
        return taskResponse;

    }
    public static Task taskMap(TaskRequest taskRequest){
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setStatus(TaskStatus.PENDING);
        return task;
    }
    public static StartTaskResponse startResponseMap(Task task){
       StartTaskResponse startTaskResponse= new StartTaskResponse();
       startTaskResponse.setTitle(task.getTitle());
        startTaskResponse.setId(task.getId());
        startTaskResponse.setStatus(TaskStatus.IN_PROGRESS);
        startTaskResponse.setStartTime(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(task.getStartTime()));
        return startTaskResponse;

    }
}
