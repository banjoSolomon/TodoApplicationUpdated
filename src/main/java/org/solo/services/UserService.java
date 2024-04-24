package org.solo.services;

import org.solo.dto.*;
import org.solo.response.*;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    TaskResponse createTask(TaskRequest taskRequest);

    StartTaskResponse startTask(StartTaskRequest startTaskRequest);

    MarkTaskResponse markTaskAsCompleted(MarkTaskRequest markTaskRequest);
}
