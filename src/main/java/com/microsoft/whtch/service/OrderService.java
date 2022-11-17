package com.microsoft.whtch.service;

import java.util.List;

public interface OrderService {
    Long getOrderTotalPrice(List<Long> ids);
}
