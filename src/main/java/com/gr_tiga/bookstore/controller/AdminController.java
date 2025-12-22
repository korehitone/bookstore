package com.gr_tiga.bookstore.controller;

import org.springframework.web.bind.annotation.*;

import com.gr_tiga.bookstore.model.table.Admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.gr_tiga.bookstore.domain.service.AdminService;
import com.gr_tiga.bookstore.helper.ErrorResponse;

// import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService as;

    public AdminController(AdminService as) {
        this.as = as;
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "Get Admin detail", description = "Get Admin detail based on ID ")
    public Admin getAdmin(@PathVariable Integer id) {
        return as.getById(id);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)) }),
            @ApiResponse(responseCode = "409", description = "DATA_CONFLICT", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "Add new Admin", description = "Add new Admin into system")
    public Admin signUp(@RequestBody Admin admin) {
        return as.insert(admin);
    }

    @GetMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "Login into Admin", description = "Get data that match login data for validation")
    public Admin signIn(@RequestHeader String email, @RequestHeader String password) {
        return as.login(email, password);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "Update Admin", description = "update admin data based on id")
    public Admin update(@PathVariable Integer id, @RequestBody Admin admin) {
        return as.update(id, admin);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "delete admin", description = "delete admin data based on id")
    public void delete(@PathVariable Integer id) {
        as.delete(id);
    }
}
