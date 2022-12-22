package com.microsoft.whtch.presentation.backoffice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.whtch.application.OrderService;
import com.microsoft.whtch.application.impl.DefaultOrderService;
import com.microsoft.whtch.domain.Order;
import com.microsoft.whtch.domain.OrderPriceCalculator;
import com.microsoft.whtch.domain.OrderRepository;
import com.microsoft.whtch.domain.WatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderBackControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldStatusIsOk() throws Exception {
        OrderRepository orderRepository = mock(OrderRepository.class);
        WatchRepository watchRepository = mock(WatchRepository.class);
        OrderPriceCalculator orderPriceCalculator = mock(OrderPriceCalculator.class);

        OrderService  orderService =
                new DefaultOrderService(watchRepository, orderRepository, orderPriceCalculator);

        Order order = Order.place(1L);
        when(orderRepository.findById(1L)).thenReturn(order);


        OrderBackController controller = new OrderBackController(orderService);
        controller.acceptOrder("1");
        String id = "1";
        this.mockMvc.perform(post("/backoffice/orders/1/accept")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(id)))
                .andExpect(status().isOk());


    }
}