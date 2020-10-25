package com.example.sampleproject.handler;

import com.example.sampleproject.controller.AccountController;
import com.example.sampleproject.controller.AccountTransactionController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(assignableTypes = {AccountController.class, AccountTransactionController.class})
public class MainExceptionHandler {

    @ExceptionHandler(AccountsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse AccountsNotFound(){
        ErrorResponse errResponse = new ErrorResponse();
        errResponse.setErrorCode(404);
        errResponse.setErrorMessage("Could not find this user's accounts");
        return errResponse;
    }

    @ExceptionHandler(TransactionsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse TransactionsNotFound(){
        ErrorResponse errResponse = new ErrorResponse();
        errResponse.setErrorCode(404);
        errResponse.setErrorMessage("Could not find transactions for this account");
        return errResponse;
    }

    @ExceptionHandler(UpdateFailureException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse UpdateFailure(){
        ErrorResponse errResponse = new ErrorResponse();
        errResponse.setErrorCode(500);
        errResponse.setErrorMessage("Create or Update failure due to server error.");
        return errResponse;
    }
}
