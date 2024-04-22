package org.solo.services;

import org.solo.dto.TaskRequest;
import org.solo.models.Task;

public interface TaskService {
    Task createTaskWith(TaskRequest taskRequest);

    Task findTaskById(String id);

    void updateTask(Task foundTask);
}
