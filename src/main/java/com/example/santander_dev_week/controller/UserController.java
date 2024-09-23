package com.example.santander_dev_week.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.santander_dev_week.model.User;
import com.example.santander_dev_week.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Tag(name = "Usuario",description = "Endpoint do usuario")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Operation(
        summary = "Busca o usuario pelo id", 
        description = "Busca o usuario Atraves do Id Solicitado",
        responses = {
            @ApiResponse(responseCode = "200",description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400" ,description = "Usuario não encontrado ")},
        parameters = {@Parameter(name = "ID" ,required = true)})
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
       var user = userService.findById(id);
       return ResponseEntity.ok(user);
    }
    @Operation(
        summary = "Salvar o usuario", 
        description = "Salvar o usuario no banco de dados ",
        responses = {
            @ApiResponse(responseCode = "200",description = "Usuario criado com sucesso "),
            @ApiResponse(responseCode = "400" ,description = "Usuario não pode ser criado ")},
            parameters = {@Parameter(name = "Usuario" ,required = true)})
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        var userCreated = userService.save(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
}