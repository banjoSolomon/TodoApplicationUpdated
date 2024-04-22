package org.solo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
public class Task {
    @Id
    private String id;
    private String title;
    private TaskStatus status;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private LocalDateTime dateUpdated = LocalDateTime.now();
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
