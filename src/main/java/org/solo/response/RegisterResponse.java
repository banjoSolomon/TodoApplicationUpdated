package org.solo.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private String username;
    private String id;
    private String dateRegistered;
}
