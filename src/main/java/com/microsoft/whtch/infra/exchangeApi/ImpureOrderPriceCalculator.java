package com.microsoft.whtch.infra.exchangeApi;

import com.microsoft.whtch.domain.Money;
import com.microsoft.whtch.domain.Order;
import com.microsoft.whtch.domain.OrderLine;
import com.microsoft.whtch.domain.OrderPriceCalculator;
import org.springframework.stereotype.Service;

@Service
public class ImpureOrderPriceCalculator implements OrderPriceCalculator {
    public Money calculateTotalPrice(Order order) {
        Money total = Money.of(0L);

        for (OrderLine orderLine : order.getOrderLines()) {
            total = total.add(orderLine.calculatePrice());
        }

        // We are allowed to contact the infra layer here
        // Get Dollar-Euro exchange rate from exchange.com
        // multiply total with exchange rate

        return total;
    }
}
