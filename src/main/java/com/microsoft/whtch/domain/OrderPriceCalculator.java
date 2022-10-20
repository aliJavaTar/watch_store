package com.microsoft.whtch.domain;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class OrderPriceCalculator {
    public Long calculate(List<Watch> watches, List<Long> ids) {

        Map<Long, Long> watchCounts = countModels(ids);

        Long price = watchCounts.entrySet().stream()
                .map(w -> w.getValue() * getPrice(watches, w))
                .mapToLong(p -> p).sum();

        Long discount = watchCounts.entrySet().stream()
                .filter((x) -> this.hasDiscount(watches, x))
                .map((x) -> this.calculateWatchDiscount(watches, x))
                .mapToLong(p -> p).sum();

        return price - discount;
    }

    private long calculateWatchDiscount(List<Watch> watches, Map.Entry<Long, Long> w) {
        return w.getValue() / getDiscount(watches, w).getCount() * getDiscount(watches, w).getAmountDiscount();
    }

    private boolean hasDiscount(List<Watch> watches, Map.Entry<Long, Long> w) {
        return getWatch(watches, w).getDiscount().getCount() != 0 &&
                w.getValue() >= getWatch(watches, w).getDiscount().getCount();
    }

    private Long getPrice(List<Watch> watches, Map.Entry<Long, Long> watch) {
        return getWatch(watches, watch).getPrice();
    }

    private Watch getWatch(List<Watch> watches, Map.Entry<Long, Long> w) {
        return watches.stream().filter(ww -> ww.getId().equals(w.getKey())).findFirst()
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    private Discount getDiscount(List<Watch> watches, Map.Entry<Long, Long> watch) {
        return getWatch(watches, watch).getDiscount();
    }

    private Map<Long, Long> countModels(List<Long> ids) {
        return ids.stream().collect(groupingBy(k -> k, counting()));
    }
}
