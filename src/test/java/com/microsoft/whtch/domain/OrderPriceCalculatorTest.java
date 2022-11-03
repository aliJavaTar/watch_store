package com.microsoft.whtch.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class OrderPriceCalculatorTest {
    private OrderPriceCalculator orderPriceCalculator;
    private List<Watch> watches;
    private List<Long> ids;
    private List<Watch> michailList;
    private List<Long> michailIds;
    private List<Watch> rolexList;
    private List<Long> rolexIds;

    @BeforeEach
    void setUp() {
        orderPriceCalculator = new OrderPriceCalculator();
        watches = new LinkedList<>();
        ids = new LinkedList<>();
        michailIds = new LinkedList<>();
        michailList = new LinkedList<>();
        rolexList = new LinkedList<>();
        rolexIds = new LinkedList<>();
    }

    @Test
    void shouldCalculatePriceForAllWatchScenario() {
        addIdOfWatchToList();

        watchLonging(2L, "michail", 80L, 40L);

        rolexWatchCrate(watches);

        watchLonging(3L, "swatch", 50L, 0L);

        watchLonging(4L, "casio", 30L, 0L);

        Long actual = orderPriceCalculator.calculate(watches, ids);

        Assertions.assertThat(actual).isEqualTo(400L);
    }

    private void watchLonging(long id, String name, long price, long amountDiscount) {
        Watch watch = Watch.create(id, name, price);
        watches.add(watch);
        Discount discount = Discount.create(id, amountDiscount, id);
        watch.applyDiscount(discount);
        discount.setWatch(watch);
    }

    private void rolexWatchCrate(List<Watch> watches) {
        Watch rolexWatch = Watch.create(1L, "rolex", 100L);
        watches.add(rolexWatch);
        watches.add(rolexWatch);
        watches.add(rolexWatch);
        Discount rolexDiscount = Discount.create(1L, 100L, 3L);
        rolexWatch.applyDiscount(rolexDiscount);
        rolexDiscount.setWatch(rolexWatch);
    }

    private void addIdOfWatchToList() {
        ids.add(2L);
        ids.add(2L);
        ids.add(1L);
        ids.add(1L);
        ids.add(1L);
        ids.add(3L);
        ids.add(4L);
    }

    @Test
    void shouldCalculatePriceOfRolexWithoutDiscount() {
        rolexIds.add(1L);
        rolexIds.add(1L);

        Watch rolexWatch = Watch.create(1L, "rolex", 100L);
        rolexList.add(rolexWatch);
        rolexList.add(rolexWatch);

        Discount rolexDiscount = Discount.create(1L, 100L, 3L);
        rolexWatch.applyDiscount(rolexDiscount);
        rolexDiscount.setWatch(rolexWatch);

        Long rolexPrice = orderPriceCalculator.calculate(rolexList, rolexIds);
        Assertions.assertThat(rolexPrice).isEqualTo(200L);
    }

    @Test
    void shouldCalculatePriceOfRolexWithDiscount() {

        rolexIds.add(1L);
        rolexIds.add(1L);
        rolexIds.add(1L);

        rolexWatchCrate(rolexList);
        Long rolexPrice = orderPriceCalculator.calculate(rolexList, rolexIds);
        Assertions.assertThat(rolexPrice).isEqualTo(200L);
    }

    @Test
    void shouldCalculatePriceOfMichailWithoutDiscount() {

        michailIds.add(2L);

        Watch michailWatch = Watch.create(2L, "michail", 80L);
        michailList.add(michailWatch);

        Discount michailDiscount = Discount.create(2L, 40L, 2L);

        michailWatch.applyDiscount(michailDiscount);
        michailDiscount.setWatch(michailWatch);

        Long michailPrice = orderPriceCalculator.calculate(michailList, michailIds);
        Assertions.assertThat(michailPrice).isEqualTo(80L);
    }

    @Test
    void shouldCalculatePriceOfMichailWithDiscount() {


        michailIds.add(2L);
        michailIds.add(2L);

        Watch michailWatch = Watch.create(2L, "michail", 80L);
        michailList.add(michailWatch);
        michailList.add(michailWatch);

        Discount michailDiscount = Discount.create(2L, 40L, 2L);

        michailWatch.applyDiscount(michailDiscount);
        michailDiscount.setWatch(michailWatch);

        Long michailPrice = orderPriceCalculator.calculate(michailList, michailIds);
        Assertions.assertThat(michailPrice).isEqualTo(120L);
    }
}