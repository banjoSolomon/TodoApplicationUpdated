package org.solo.services;

import org.solo.dto.LoginRequest;
import org.solo.dto.RegisterRequest;
import org.solo.dto.StartTaskRequest;
import org.solo.dto.TaskRequest;
import org.solo.response.LoginResponse;
import org.solo.response.RegisterResponse;
import org.solo.response.StartTaskResponse;
import org.solo.response.TaskResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    TaskResponse createTask(TaskRequest taskRequest);

    StartTaskResponse startTask(StartTaskRequest startTaskRequest);
}
