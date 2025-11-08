package com.example.kmartz.dto;

public record ShopKeeperDTO(
    Long shopKeeperId,
    String firstName,
    String lastName,
    String email,
    String password,
    String phoneNo,
    String address
) {
}
