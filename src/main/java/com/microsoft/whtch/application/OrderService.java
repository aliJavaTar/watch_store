package com.microsoft.whtch.application;


import java.util.List;

public interface OrderService {
    Long getOrderTotalPrice(List<Long> ids);
}
