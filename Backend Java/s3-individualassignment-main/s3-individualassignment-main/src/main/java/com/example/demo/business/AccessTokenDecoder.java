package com.example.demo.business;
import com.example.demo.domain.AccessToken;



public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
