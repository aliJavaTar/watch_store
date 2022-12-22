//package com.microsoft.whtch.infra.mysql;
//
//import com.microsoft.whtch.domain.Watch;
//import com.microsoft.whtch.domain.WatchRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class WatchMySqlRepository implements WatchRepository {
//    private final WatchMySqlJpaRepository repository;
//
//    @Override
//    public Watch findById(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("not found"));
//    }
//}
