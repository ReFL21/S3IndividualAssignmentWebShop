package com.example.demo.business.ConvertersTests;

import com.example.demo.business.Converters.UserConverter;
import com.example.demo.domain.User;
import com.example.demo.repository.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserConverterTest {

    @Test
    void ShouldConvertUserEntityToUser(){
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("Ivan40")
                .email("Ivan40@abv.bg")
                .name("Ivan")
                .build();

        User actualUser = UserConverter.convert(userEntity);

        User expectedUser = User.builder()
                .id(1L)
                .username("Ivan40")
                .email("Ivan40@abv.bg")
                .name("Ivan")
                .build();

        assertEquals(expectedUser, actualUser);
    }

}
