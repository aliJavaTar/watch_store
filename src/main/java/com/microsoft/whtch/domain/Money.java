package com.microsoft.whtch.domain;

import java.util.Objects;

public class Money {

    private final Long amount;

    private Money(Long amount) {
        this.amount = amount;
    }

    public static Money of(Long amount) {
        if (amount < 0) {
            throw new IllegalStateException("Money can't have negative value");
        }
        return new Money(amount);
    }

    public Long getAmount() {
        return amount;
    }

    public Money add(Money money) {
        return new Money(this.amount + money.getAmount());
    }

    public Money multiplyBy(int number) {
        return new Money(this.amount * number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
