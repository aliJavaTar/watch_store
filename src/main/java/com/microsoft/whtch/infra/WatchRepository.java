package com.microsoft.whtch.infra;

import com.microsoft.whtch.domain.Watch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WatchRepository extends JpaRepository<Watch, Long>, JpaSpecificationExecutor<Watch> {
    List<Watch> findAll(Specification specification);


//    List<Watch> findAllByIdIn(Collection<Long> id);

}
