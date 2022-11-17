package com.microsoft.whtch.infra.inmemory;

import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.domain.WatchRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryWatchRepository implements WatchRepository {

    private final Map<Long, Watch> watches = new HashMap<>();

    public InMemoryWatchRepository() {
        watches.put(1L, Watch.create(1L, "Rolex", 100L));
        watches.put(2L, Watch.create(2L, "Michael", 80L));
        watches.put(3L, Watch.create(3L, "Swatch", 50L));
        watches.put(4L, Watch.create(4L, "Casio", 30L));
    }

    @Override
    public Watch findById(Long id) {
        return watches.get(id);
    }
}
