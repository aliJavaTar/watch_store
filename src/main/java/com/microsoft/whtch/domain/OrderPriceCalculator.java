package com.microsoft.whtch.domain;


/**
 *                                                                          ---- Pure Service
 *       Domain Service (Live in Domain layer to do some business logic) ---
 *                                                                          ----- Infra layer Service
 *
 *       Application Service (lives in application layer to coordinate things between layers.)
 *
 */
public interface OrderPriceCalculator {

    Money calculateTotalPrice(Order order);

}
