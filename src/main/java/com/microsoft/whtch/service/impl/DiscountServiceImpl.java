package com.microsoft.whtch.service.impl;

import com.microsoft.whtch.infra.DiscountRepository;
import com.microsoft.whtch.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
}
