package com.example.Security.dto;

import lombok.Data;

@Data
public class MemberLoginRequest {
    private String memId;
    private String password;
}
