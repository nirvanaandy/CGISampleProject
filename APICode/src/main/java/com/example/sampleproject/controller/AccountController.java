package com.example.sampleproject.controller;

import com.example.sampleproject.config.AppSettingConfiguration;
import com.example.sampleproject.domain.Account;
import com.example.sampleproject.handler.AccountsNotFoundException;
import com.example.sampleproject.handler.UpdateFailureException;
import com.example.sampleproject.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @Author: Lei Ma
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/accounts", produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppSettingConfiguration appSettingConfig;

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("")
    @ResponseBody
    public CollectionModel<Account> getAllAccounts(){
        List<Account> resultList = accountService.findAll();
        return new CollectionModel(resultList);
    }

    /**
     * @Description: Query all the accounts belongs to an user id
     * @param userId
     * @param pageRequest
     * @param assembler
     * @return
     */
    @GetMapping("/querybyuser/{userid}")
    public PagedModel<EntityModel<Account>> getAccountsByUserId(@NotNull @PathVariable("userid") final int userId,
                                                                Pageable pageRequest,
                                                                PagedResourcesAssembler<Account> assembler){

        logger.info("Query accounts by userId "+userId);

        int pageSize = appSettingConfig.getPageSize();
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(),pageSize,pageRequest.getSort());

        Page<Account> accounts = accountService.findAccoutsByUserId(userId, pageable);

        if( Objects.isNull(accounts)) {
            logger.error("Failed to find account for user " + userId);
            throw new AccountsNotFoundException();
        }

        //Add links to related transactions for each account
        accounts.forEach(account -> {
            Link linkToTransactions = linkTo(methodOn(AccountTransactionController.class).getTransactionsByAccountNumber(account.getAccountNumber(), null, null)).withRel("Transactions");
            account.add(linkToTransactions);
        });

        return assembler.toModel(accounts);
    }

    @GetMapping("/{accountnumber}")
    @ResponseBody
    public EntityModel<Account> getAccountByNumber(@PathVariable("accountnumber") @NotBlank final String accountNumber){

        logger.info("Query account by number "+accountNumber);

        Account account = accountService.findAccountByNumber(accountNumber);

        if(Objects.isNull(account)) {
            logger.error("Failed to find account for number "+accountNumber);
            throw new AccountsNotFoundException();
        }
        return new EntityModel<Account>(account);
    }

    @PostMapping("")
    public EntityModel<Account> createAccount(@Valid @RequestBody Account account){

        logger.info("Create a new Account with number: " + account.getAccountNumber());

        Account result = accountService.createAccount(account);

        if(Objects.isNull(result))
            throw new UpdateFailureException();

        EntityModel<Account> resultModel = new EntityModel<>(account);
        Link selfLink = linkTo(methodOn(AccountController.class).getAccountByNumber(account.getAccountNumber())).withSelfRel();
        resultModel.add(selfLink);
        return resultModel;
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteAccountByAccountNumber(@NotBlank @PathVariable String accountNumber){

        logger.info("Delete account having number: " + accountNumber);

        Account account = accountService.findAccountByNumber(accountNumber);
        if(Objects.nonNull(account))
            accountService.deleteAccount(account);
        else
            throw new AccountsNotFoundException();
    }

}
