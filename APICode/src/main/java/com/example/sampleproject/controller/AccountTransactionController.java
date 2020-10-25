package com.example.sampleproject.controller;

import com.example.sampleproject.config.AppSettingConfiguration;
import com.example.sampleproject.domain.AccountTransViewInfo;
import com.example.sampleproject.handler.TransactionsNotFoundException;
import com.example.sampleproject.service.AccountTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @Author: Lei Ma
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/transactions", produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class AccountTransactionController {

    @Autowired
    AccountTransactionService transactionService;

    @Autowired
    private AppSettingConfiguration appSettingConfig;

    Logger logger = LoggerFactory.getLogger(AccountTransactionController.class);

    @GetMapping("/querybyaccount/{accountNumber}")
    public PagedModel<EntityModel<AccountTransViewInfo>> getTransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber,
                                                                                        Pageable pageRequest,
                                                                                        PagedResourcesAssembler<AccountTransViewInfo> assembler){

        logger.info("Query transactions for account "+accountNumber);

        int pageSize = appSettingConfig.getPageSize();
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(),pageSize,pageRequest.getSort());

        long start = System.nanoTime();
        Page<AccountTransViewInfo> transactions = transactionService.findTransactionByAccountNumberUsingView(accountNumber, pageable);
        long end = System.nanoTime();
        System.out.println("used "+(end - start));
        if(Objects.isNull(transactions)) {
            logger.error("Failed to find transactions for account "+accountNumber);
            throw new TransactionsNotFoundException();
        }

        //Add links to belonged account for each transaction record
        transactions.forEach(transaction->{
            Link linkToAccount = linkTo(methodOn(AccountController.class).getAccountsByUserId(transaction.getAccountUserId(),null,null)).withRel("belongedAccount");
            transaction.add(linkToAccount);
        });

        return assembler.toModel(transactions);
    }
}
