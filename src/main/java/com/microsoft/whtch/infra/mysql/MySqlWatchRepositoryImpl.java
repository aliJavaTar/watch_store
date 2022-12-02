package com.microsoft.whtch.infra.mysql;

import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.domain.WatchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class MySqlWatchRepositoryImpl implements WatchRepository {
    private final MySqlWatchRepository  repository;

    public MySqlWatchRepositoryImpl(MySqlWatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public Watch findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
}
