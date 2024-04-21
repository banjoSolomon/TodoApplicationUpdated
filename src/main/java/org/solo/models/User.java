package org.solo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Document("User")
public class User {
    private String username;
    private String firstName;
    private String password;
    private List<Task> tasks = new ArrayList<>();
    @Id
    private String id;
    private  LocalDateTime dateRegistered = LocalDateTime.now();
}
