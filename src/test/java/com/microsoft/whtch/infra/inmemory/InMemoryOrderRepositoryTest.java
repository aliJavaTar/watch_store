package com.microsoft.whtch.infra.inmemory;

import com.microsoft.whtch.domain.Money;
import com.microsoft.whtch.domain.Order;
import com.microsoft.whtch.domain.Watch;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryOrderRepositoryTest {

    @Test
    void shouldSaveAndFindOrderById() {
        Watch rolex = Watch.create(1L, "Rolex", Money.of(100L));

        Order order = Order.place(1L);
        order.addOrderLine(rolex, 2);

        InMemoryOrderRepository repository = new InMemoryOrderRepository();

        repository.save(order);

        Order retrievedOrder = repository.findById(1L);

        assertThat(retrievedOrder.getId()).isEqualTo(order.getId());
    }

}