package org.solo.response;

import lombok.Data;
import org.solo.models.TaskStatus;
@Data
public class TaskResponse {
    private String title;
    private TaskStatus status;
    private String id;
    private String dateCreated;

}
