package com.example.demo.business.Converters;

import com.example.demo.domain.User;
import com.example.demo.repository.UserEntity;

public class UserConverter {

    public UserConverter(){}
    public static User convert(UserEntity userEntity){
       return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }
}
