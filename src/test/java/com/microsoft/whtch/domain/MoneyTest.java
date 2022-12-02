package com.microsoft.whtch.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @Test
    void shouldCreateMoney() {
        Money money = Money.of(100L);

        assertThat(money.getAmount()).isEqualTo(100L);
    }

    @Test
    void shouldAddTwoMonies() {
        Money moneyOne = Money.of(100L);
        Money moneyTwo = Money.of(50L);

        Money addedResult = moneyOne.add(moneyTwo);

        assertThat(addedResult.equals(Money.of(150L))).isTrue();
    }

    @Test
    void shouldMultipleAMoneyByNTimes() {
        Money money = Money.of(100L);

        Money multipliedResult = money.multiplyBy(3);

        assertThat(multipliedResult.equals(Money.of(300L))).isTrue();
    }

}