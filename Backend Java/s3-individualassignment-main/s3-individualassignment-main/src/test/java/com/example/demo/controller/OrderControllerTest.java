package com.example.demo.controller;

import com.example.demo.business.CreateOrderUseCase;
import com.example.demo.business.DeleteOrderUseCase;
import com.example.demo.business.GetAllOrdersUseCase;
import com.example.demo.business.GetOrdersByUserIdUseCase;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderRequest;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderResponse;
import com.example.demo.domain.OrdersRequestsAndResponse.GetAllOrdersResponse;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DeleteOrderUseCase deleteOrderUseCase;
    @MockBean
    private CreateOrderUseCase createOrderUseCase;
    @MockBean
    private GetAllOrdersUseCase getAllOrdersUseCase;
    @MockBean
    private GetOrdersByUserIdUseCase getOrdersByUserIdUseCase;


    @Test
    @WithMockUser(username = "Ivan40",roles = {"Customer"})
    void createOrder_shouldReturn201_whenIsValid() throws Exception {
        CreateOrderRequest request = CreateOrderRequest.builder()
                .date("18-02-2013")
                .user(User.builder().id(1L).build())
                .ticket(Tickets.builder().id(3L).build())

                .quantity(2)
                .build();

        when(createOrderUseCase.createOrder(request)).thenReturn(CreateOrderResponse.builder().id(1L).build());

        mockMvc.perform(post("/orders/registerOrder").contentType(APPLICATION_JSON_VALUE).content(
                """
                    {
                    "ticket":{"id":3},
                    "user":{"id":1},
                    "date":"18-02-2013",    
                     "quantity":2,
                     "price":50   
                        
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


        verify(createOrderUseCase).createOrder(request);
    }




    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void deleteOrder_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/orders/5"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteOrderUseCase).deleteOrder(5L);
    }

    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void getAllOrdersShouldReturnListOfOrders() throws Exception {
        GetAllOrdersResponse response = GetAllOrdersResponse.builder()
                .orders(List.of(
                        Order.builder().id(2L).price(50).quantity(1).user(User.builder().id(4L).build()).tickets(Tickets.builder().id(3L).build()).date("24-01-2007").build(),
                        Order.builder().id(3L).price(100).quantity(2).user(User.builder().id(4L).build()).tickets(Tickets.builder().id(3L).build()).date("24-01-2022").build()
                ))
                .build();
        when(getAllOrdersUseCase.getAllOrders()).thenReturn(response);

        mockMvc.perform(get("/orders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                            {    
                             "orders": [
                             {
                             "id":2,
                             "tickets": {"id" : 3},
                             "user": {"id": 4},
                             "date": "24-01-2007",
                             "quantity":1 ,
                             "price":50 
                             },
                             {
                             "id":3,
                             "tickets": {"id" : 3},
                             "user": {"id": 4},
                             "date": "24-01-2022",
                             "quantity":2 ,
                             "price":100 
                             }
                             ]   
                            
                                
                            
                            }
                            """

                ));


    }

    @Test
    void getOrdersByUserId_shouldReturnListOfOrders(){




    }




}
