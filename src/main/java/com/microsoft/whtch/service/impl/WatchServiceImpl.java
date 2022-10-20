package com.microsoft.whtch.service.impl;


import com.microsoft.whtch.domain.OrderPriceCalculator;
import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.infra.mysql.WatchRepository;
import com.microsoft.whtch.infra.mysql.specification.WatchSpecifications;
import com.microsoft.whtch.service.WatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchServiceImpl implements WatchService {
    private final WatchRepository watchRepository;
    private final WatchSpecifications watchSpecifications;

    @Override
    public Long getWatchPrice(List<Long> ids) {
        List<Watch> watches = watchRepository.findAll(watchSpecifications.findAll(ids));
        OrderPriceCalculator orderPriceCalculator = new OrderPriceCalculator();
        return orderPriceCalculator.calculate(watches, ids);
    }

}