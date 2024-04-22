package org.solo.response;

import lombok.Data;
import org.solo.models.TaskStatus;

@Data
public class StartTaskResponse {
    private String title;
    private String id;
    private TaskStatus status;
    private String startTime;
}
