package com.gr_tiga.bookstore.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr_tiga.bookstore.domain.service.CustomerService;
import com.gr_tiga.bookstore.helper.ErrorResponse;
import com.gr_tiga.bookstore.model.table.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/user")
public class CustomerController {

    private final CustomerService cs;

    public CustomerController(CustomerService cs) {
        this.cs = cs;
    }

    @GetMapping("/{uid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "Get customer detail", description = "Get customer detail based on uid ")
    public Customer getCustomer(@PathVariable String uid) {
        return cs.getByUid(uid);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "409", description = "DATA_CONFLICT", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "add new customer", description = "add new customer into system")
    public Customer signUp(@RequestBody Customer customer) {
        return cs.insert(customer);
    }

    @GetMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "login into customer", description = "Get data that match login data for validation")
    public Customer signIn(@RequestHeader String email, @RequestHeader String password) {
        return cs.login(email, password);
    }

    @PutMapping("/{uid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "update customer", description = "update customer data based on uid")
    public Customer update(@PathVariable String uid, @RequestBody Customer customer) {
        return cs.update(uid, customer);
    }

    @DeleteMapping("/{uid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "delete customer", description = "delete customer data based on uid")
    public void delete(@PathVariable String uid) {
        cs.delete(uid);
    }
}
