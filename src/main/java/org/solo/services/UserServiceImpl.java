package org.solo.services;

import org.solo.dto.LoginRequest;
import org.solo.dto.RegisterRequest;
import org.solo.dto.TaskRequest;
import org.solo.exceptions.*;
import org.solo.models.Task;
import org.solo.models.User;
import org.solo.repository.Users;
import org.solo.response.LoginResponse;
import org.solo.response.RegisterResponse;
import org.solo.response.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.solo.utilities.Mapper.*;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
   private Users users;
    @Autowired
    private TaskService taskService;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        validate(registerRequest.getUsername());
        User newUser = registerMap(registerRequest);
        User savedUser = users.save(newUser);
        return registerResponseMap(savedUser);

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User foundUser = findUserBy(loginRequest.getUsername());
        if(foundUser.getPassword().equals(loginRequest.getPassword())){
            return loginResponseMap(foundUser);
        }else
            throw new InvalidUsernameOrPasswordException("Invalid username or password");
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        User foundUser = findUserBy(taskRequest.getUsername());
        if(taskExistsForUser(foundUser, taskRequest.getTitle()))
            throw new TaskExistsException("Task already exists");
        Task newTask = taskService.createTaskWith(taskRequest);
        foundUser.getTasks().add(newTask);
        users.save(foundUser);
        return taskResponseMap(newTask);
    }

    private boolean taskExistsForUser(User foundUser, String title) {
        for(Task task : foundUser.getTasks())
            if(task.getTitle() != null && task.getTitle().equals(title))
                return true;
        return false;
    }

    private User findUserBy(String username) {
        if(username == null) throw new UsernameCannotBeNullException("User cannot be null");
        User foundUser = users.findByUsername(username);
        if(foundUser == null) throw new UserNotFoundException(String.format("%s not found", username));
        return foundUser;

    }

    private void validate(String username) {
        boolean userExist = users.existsByUsername(username);
        if (userExist) throw new UserExistException("username already exists");
    }
}
