package org.solo.response;

import lombok.Data;
import org.solo.models.TaskStatus;
@Data
public class MarkTaskResponse {
    private String title;
    private String id;
    private TaskStatus status;
    private String endTime;


}
