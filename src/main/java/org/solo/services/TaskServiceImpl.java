package org.solo.services;

import org.solo.dto.TaskRequest;
import org.solo.models.Task;
import org.solo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.solo.utilities.Mapper.taskMap;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task createTaskWith(TaskRequest taskRequest) {
        Task task = taskMap(taskRequest);
        return taskRepository.save(task);
    }

    @Override
    public Task findTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void updateTask(Task foundTask) {
        taskRepository.save(foundTask);

    }
}
