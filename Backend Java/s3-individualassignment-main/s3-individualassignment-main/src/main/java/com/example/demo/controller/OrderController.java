package com.example.demo.controller;

import com.example.demo.business.*;
import com.example.demo.configuration.security.Isauthenticated.IsAuthenticated;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderRequest;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderResponse;
import com.example.demo.domain.OrdersRequestsAndResponse.GetAllOrdersResponse;
import com.example.demo.domain.OrdersRequestsAndResponse.GetOrdersByUserIdResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private GetOrdersByUserIdUseCase getOrdersByUserIdUseCase;
    @Autowired
    private GetAllOrdersUseCase getAllOrdersUseCase;
    @Autowired
    private CreateOrderUseCase createOrderUseCase;
    @Autowired
    private DeleteOrderUseCase deleteTicketUseCase;

    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @GetMapping
    public ResponseEntity<GetAllOrdersResponse> getAllOrders(){
        GetAllOrdersResponse response = getAllOrdersUseCase.getAllOrders();
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"Customer"})
    @PostMapping("/registerOrder")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request){
        CreateOrderResponse response = createOrderUseCase.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @RolesAllowed({"Customer"})
    @GetMapping("/{id}")
    public ResponseEntity<GetOrdersByUserIdResponse> getOrdersByUserId(@PathVariable(value = "id") final Long id){
        GetOrdersByUserIdResponse response = getOrdersByUserIdUseCase.getOrdersByUserId(id);
        return ResponseEntity.ok(response);

    }

    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
            deleteTicketUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}
