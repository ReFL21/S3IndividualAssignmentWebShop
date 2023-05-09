package com.example.demo.business;
import com.example.demo.domain.AccessToken;
public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
