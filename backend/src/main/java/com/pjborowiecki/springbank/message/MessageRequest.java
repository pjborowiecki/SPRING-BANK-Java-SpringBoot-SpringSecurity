package com.pjborowiecki.springbank.message;

public record MessageRequest(
        String name,
        String email,
        String subject,
        String message) {

}
