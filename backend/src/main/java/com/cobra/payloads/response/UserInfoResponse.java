package com.cobra.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserInfoResponse {
    private String id;
    private String username;
    private String email;
    private List<String> roles;

}