package com.microsoft.whtch.infra.mysql;

import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.domain.WatchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class MySqlWatchRepository implements WatchRepository {
    private final JpaRepository<Watch, Long> repository;

    public MySqlWatchRepository(JpaRepository<Watch, Long> repository) {
        this.repository = repository;
    }

    @Override

    public Watch findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
}
