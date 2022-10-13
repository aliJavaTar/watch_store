package com.microsoft.whtch.service.impl;


import com.microsoft.whtch.domain.Discount;
import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.infra.WatchRepository;
import com.microsoft.whtch.infra.specification.WatchSpecifications;
import com.microsoft.whtch.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class WatchServiceImpl implements WatchService {
    private final WatchRepository watchRepository;
    private final WatchSpecifications watchSpecifications;
    private Map<Long, Long> countOfWatchName = new HashMap<>();
    private List<Watch> watches = new ArrayList<>();

    @Override
    public Long getWatchPrice(List<Long> ids) {
        countOfWatchName = countModels(ids);
        watches = watchRepository.findAll(watchSpecifications.findAll(ids));
        System.out.println(calculateDiscount());
        return calculateTotalPriceWithDiscount();
    }

    private Map<Long, Long> countModels(List<Long> ids) {
        return ids.stream().collect(groupingBy(k -> k, counting()));
    }


    private Long calculateTotalPriceWithDiscount() {
        return calculatePrice() - calculateDiscount();
    }

    private long calculatePrice() {
        return countOfWatchName.entrySet().stream()
                .map(w -> w.getValue() * getPrice(w))
                .mapToLong(price -> price).sum();
    }

    private Long getPrice(Map.Entry<Long, Long> watch) {
        return getWatch(watch).getPrice();
    }

    private Discount getDiscount(Map.Entry<Long, Long> watch) {
        return getWatch(watch).getDiscount();
    }

    private Long calculateDiscount() {
        return countOfWatchName.entrySet().stream()
                .filter(this::hasDiscount)
                .map(this::calculateWatchDiscount)
                .mapToLong(price -> price).sum();
    }

    private long calculateWatchDiscount(Map.Entry<Long, Long> w) {
        return w.getValue() / getDiscount(w).getCount() * getDiscount(w).getAmountDiscount();
    }

    private boolean hasDiscount(Map.Entry<Long, Long> w) {
        return getWatch(w).getDiscount().getCount() != 0 &&
                w.getValue() >= getWatch(w).getDiscount().getCount();
    }

    private Watch getWatch(Map.Entry<Long, Long> w) {
        return watches.get(w.getKey().intValue() - 1);
    }
}