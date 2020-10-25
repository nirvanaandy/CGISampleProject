package com.example.sampleproject.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No accounts for this user found.")
public class AccountsNotFoundException extends RuntimeException{
}
