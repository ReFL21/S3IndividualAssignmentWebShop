package com.example.demo.business.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCredentialsExeptions  extends ResponseStatusException {
public InvalidCredentialsExeptions(){
    super(HttpStatus.BAD_REQUEST, "Invalid_Credentials");

}
}
