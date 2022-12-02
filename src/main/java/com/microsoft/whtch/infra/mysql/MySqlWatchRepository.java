package com.microsoft.whtch.infra.mysql;

import com.microsoft.whtch.domain.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySqlWatchRepository extends JpaRepository<Watch, Long> {
}
