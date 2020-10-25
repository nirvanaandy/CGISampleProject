package com.example.sampleproject.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No transactions found for this account.")
public class TransactionsNotFoundException extends RuntimeException{
}
