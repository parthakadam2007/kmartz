package com.example.kmartz.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String jwtToken;
    private String username;
    private Long customerId;
    private Long shopKeeperId;
}
