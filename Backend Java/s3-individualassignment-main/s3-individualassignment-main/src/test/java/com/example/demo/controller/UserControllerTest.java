package com.example.demo.controller;

import com.example.demo.business.CreateUserUseCase;
import com.example.demo.business.DeleteUserUseCase;
import com.example.demo.business.GetAllUsersUseCase;
import com.example.demo.business.GetUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRequestsAndResponce.CreateUserRequest;
import com.example.demo.domain.UserRequestsAndResponce.CreateUserResponse;
import com.example.demo.domain.UserRequestsAndResponce.GetAllUsersResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateUserUseCase createUserUseCase;
    @MockBean
    private DeleteUserUseCase deleteUserUseCase;
    @MockBean
    private GetAllUsersUseCase getAllUsersUseCase;
    @MockBean
    GetUserUseCase getUserUseCase;



    @Test
    @WithMockUser(username = "Ivan40",roles = {"Customer"})
    void createUser_shouldReturn201_whenisValid()throws Exception{
        CreateUserRequest request = CreateUserRequest.builder()
                .name("Ivan")
                .email("Ivan40@gmail.com")
                .username("Ivan40")
                .password("asdasd")
                .build();

        when(createUserUseCase.createUser(request)).thenReturn(CreateUserResponse.builder().id(1L).build());


        mockMvc.perform(post("/users/register").contentType(APPLICATION_JSON_VALUE).content(
                                """
                                { 
                                "name": "Ivan",
                                "username": "Ivan40",
                                "email": "Ivan40@gmail.com",
                                "password": "asdasd"
                                
                                }
                                """
        )).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type",APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                            """
                          
                                     {"id": 1}
                                     """
                ));


        verify(createUserUseCase).createUser(request);

    }
    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void deleteUser_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/users/5"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteUserUseCase).deleteUser(5L);
    }
    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void getAllUsers_shouldReturnListOfUsers() throws Exception {
        GetAllUsersResponse response = GetAllUsersResponse.builder()
                .users(List.of(
                        User.builder().id(1L).username("Ivan40").name("Ivan").email("Ivan40@gmail.com").build(),
                        User.builder().id(3L).username("DebelashkaIstoriq").name("Georgi").email("edinDebel@gmail.com").build()
                ))
                .build();

        when(getAllUsersUseCase.getAllUsers()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                            {
                            "users": [
                            {
                            "id": 1,
                            "username": "Ivan40",
                            "name":"Ivan",
                            "email":"Ivan40@gmail.com"
                            },
                            {
                            "id": 3,
                            "username": "DebelashkaIstoriq",
                            "name":"Georgi",
                            "email":"edinDebel@gmail.com"
                            }
                            
                            ]
                            
                            }
                            """

                ));


    }


    @Test
    @WithMockUser(username = "Ivan40",roles = {"Customer"})
    void getUserShouldReturnValidUser() throws Exception {
    User user = User.builder()
            .id(1L)
            .username("Ivan40")
            .name("Ivan")
            .email("Ivan40@gmail.com")
            .build();

    when(getUserUseCase.getUser(1L)).thenReturn(Optional.of(user));
    mockMvc.perform(get("/users/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
            .andExpect(content().json(
                    """
                            {
                            "id": 1,
                            "username": "Ivan40",
                            "name": "Ivan",
                            "email": "Ivan40@gmail.com"
                            }
                            """
            ));
    verify(getUserUseCase).getUser(1L);

    }

    @Test
    @WithMockUser(username = "Ivan40",roles = {"Customer"})
    void getUser_shouldReturn404Error_whenUserNotFound() throws Exception {
        when(getUserUseCase.getUser(12L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/users/12"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getUserUseCase).getUser(12L);
    }


}
