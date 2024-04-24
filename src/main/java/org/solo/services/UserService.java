package org.solo.services;

import org.solo.dto.*;
import org.solo.response.LoginResponse;
import org.solo.response.RegisterResponse;
import org.solo.response.StartTaskResponse;
import org.solo.response.TaskResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    TaskResponse createTask(TaskRequest taskRequest);

    StartTaskResponse startTask(StartTaskRequest startTaskRequest);

    void markTaskAsCompleted(MarkTaskRequest markTaskRequest);
}
