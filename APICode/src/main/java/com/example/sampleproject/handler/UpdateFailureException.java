package com.example.sampleproject.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="No accounts for this user found.")
public class UpdateFailureException extends RuntimeException{
}
