package com.example.magjong.dto;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
