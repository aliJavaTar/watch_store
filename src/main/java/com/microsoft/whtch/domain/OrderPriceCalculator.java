package com.microsoft.whtch.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class OrderPriceCalculator {
    public Long calculate(List<Watch> watches, List<Long> ids) {

        Map<Long, Long> watchCounts = ids.stream().collect(groupingBy(k -> k, counting()));

        Supplier<RuntimeException> not_found = () -> new RuntimeException("not found");

        Long price = watchCounts.entrySet().stream()
                .map(w -> w.getValue() * watches.stream()
                        .filter(ww -> ww.getId().equals(w.getKey())).findFirst()
                        .orElseThrow(not_found).getPrice())
                         .mapToLong(p -> p).sum();

        Long discount = watchCounts.entrySet().stream()
                .filter((x) -> {
                    Watch getWatch = watches.stream().filter(ww -> ww.getId().equals(x.getKey())).findFirst()
                     .orElseThrow(not_found);
                    return getWatch.getDiscount().getCount() != 0 &&
                            x.getValue() >= getWatch.getDiscount().getCount();
                })
                .map((x) -> {
                    Optional<Watch> findFirst = watches.stream().filter(ww -> ww.getId().equals(x.getKey())).findFirst();
                    return x.getValue() / findFirst
                            .orElseThrow(not_found)
                            .getDiscount().getCount() * findFirst
                            .orElseThrow(not_found).getDiscount().getAmountDiscount();
                })
                .mapToLong(p -> p).sum();

        return price - discount;
    }
}
