package com.pjborowiecki.springbank.customer;

public record CustomerRequest(String email, String password, String role) {
}
