package com.example.kmartz.dto;

public record CustomerDTO(Long customerId, String firstName, String lastName, String email,String password, String phoneNo, String address) {
}
