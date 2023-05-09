package com.example.demo.controller;

import com.example.demo.business.*;
import com.example.demo.configuration.security.Isauthenticated.IsAuthenticated;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRequestsAndResponce.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;
@RestController
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/users")

public class UserController {
    @Autowired
    private GetAllUsersUseCase getAllUsersUseCase;

    @Autowired
    private GetUserUseCase getUserUseCase;

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @Autowired
    private LoginUseCase loginUseCase;

    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersResponse response = getAllUsersUseCase.getAllUsers();
        return ResponseEntity.ok(response);
    }
    @IsAuthenticated
    @RolesAllowed({"Customer"})
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final Long id) {
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }


    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest userRequest){
        CreateUserResponse response = createUserUseCase.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        LoginResponse response = loginUseCase.login(request);
        return ResponseEntity.ok(response);
    }


}
