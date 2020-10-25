package com.example.sampleproject;

import com.example.sampleproject.domain.*;
import com.example.sampleproject.service.AccountService;
import com.example.sampleproject.service.AccountTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.annotation.Resource;
import java.util.Map;



@SpringBootTest
@AutoConfigureMockMvc
class SampleprojectApplicationTests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountTransactionService transactionService;

    @Resource
    private MockMvc mockMvc;

    @DisplayName("MT for API /accounts/querybyuser/{userid}")
    @Test
    @Order(50)
    public void testAPIQueryAccountsByUserId()  throws Exception{

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/querybyuser/123").contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(200,status);

        String content = response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map result = (Map)mapper.readValue(content,Object.class);

        int totalElements = (int)((Map)result.get("page")).get("totalElements");
        Assertions.assertEquals(8,totalElements);
    }

    @DisplayName("MT for API /transactions/querybyaccount/{accountNumber}")
    @Test
    @Order(40)
    public void testAPIQueryTransactionsByAccountNumber() throws Exception{

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/transactions/querybyaccount/585309209").contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(200,status);

        String content = response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map result = (Map)mapper.readValue(content,Object.class);

        int totalElements = (int)((Map)result.get("page")).get("totalElements");
        Assertions.assertEquals(8,totalElements);
    }

    @DisplayName("UT for method findAccoutsByUserId(): Account Service queryby user id pageable")
    @Test
    @Order(30)
    void testGetAccountsByUserIdPageable(){

        Pageable pageable = PageRequest.of(0,3 , Sort.by(Sort.Direction.ASC,"accountNumber"));
        Page<Account> accounts = accountService.findAccoutsByUserId(123,pageable);

        int size = accounts.getTotalPages();
        Assertions.assertEquals(3, size,"Not enough accounts for the user 123 queried.");

        long totalRecords = accounts.getTotalElements();
        Assertions.assertEquals(8, totalRecords,"Got wrong amount of accounts for the user 123");

        while(true){

            int number = accounts.getNumber();
            int numberOfElemnts = accounts.getNumberOfElements();
            int pageSize = accounts.getSize();
            System.out.printf("Account page info - page number %s, numberOfElements: %s, size: %s, totalPages: %s%n",
                    number, numberOfElemnts,pageSize,size);

            accounts.forEach(account->{
                System.out.println(account.toString());
            });

            if(!accounts.hasNext())
                break;

            pageable = accounts.nextPageable();
            accounts = accountService.findAccoutsByUserId(123,pageable);
        }
    }

    @DisplayName("UT for method findTransactionsByAccountNumber(): Transaction Service queryby account number using native SQL")
    @Test
    @Order(20)
    void testQueryTransactionsByAccountNumberUsingNativeSQL(){

        Pageable pageable = PageRequest.of(0,3);
        String accountNumber = "585309209";

        Page<Map> transactions = transactionService.findTransactionsByAccountNumber(accountNumber, pageable);

        int size = transactions.getTotalPages();
        Assertions.assertEquals(3,size, "Cannot find enough transactions pages for account number 585309209.");

        long totalRecords = transactions.getTotalElements();
        Assertions.assertEquals(8, totalRecords, "Cannot find enough transactions for account number 585309209.");

        while(true){

            int number = transactions.getNumber();
            int numberOfElemnts = transactions.getNumberOfElements();
            int pageSize = transactions.getSize();
            System.out.printf("Trans page info - page number %s, numberOfElements: %s, size: %s, totalPages: %s%n",
                    number, numberOfElemnts,pageSize,size);

            transactions.forEach(transMap ->{
                transMap.forEach((k,v)->{
                    System.out.print(k + " : " + v +" , ");
                });
                System.out.println();
            });

            if( !transactions.hasNext() )
                break;

            pageable = transactions.nextPageable();
            transactions = transactionService.findTransactionsByAccountNumber(accountNumber, pageable);
        }
    }

    @DisplayName("UT for method findTransactionByAccountNumberUsingView(): Transaction Service queryby account number using JPQL")
    @Test
    @Order(10)
    void testfindTransactionByAccountNumberUsingView(){
        Pageable pageable = PageRequest.of(0,3);
        String accountNumber = "585309209";

        Page<AccountTransViewInfo> transactions = transactionService.findTransactionByAccountNumberUsingView(accountNumber, pageable);

        int size = transactions.getTotalPages();
        Assertions.assertEquals(3,size, "Cannot find enough transactions pages for account number 585309209.");

        long totalRecords = transactions.getTotalElements();
        Assertions.assertEquals(8, totalRecords, "Cannot find enough transactions for account number 585309209.");

        while(true){

            int number = transactions.getNumber();
            int numberOfElemnts = transactions.getNumberOfElements();
            int pageSize = transactions.getSize();
            System.out.printf("Trans page info - page number %s, numberOfElements: %s, size: %s, totalPages: %s%n",
                    number, numberOfElemnts,pageSize,size);

            transactions.forEach(transViewInfo ->{
                System.out.println(transViewInfo.toString());
            });

            if( !transactions.hasNext() )
                break;

            pageable = transactions.nextPageable();
            transactions = transactionService.findTransactionByAccountNumberUsingView(accountNumber, pageable);
        }

    }

}
