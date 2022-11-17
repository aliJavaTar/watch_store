package com.microsoft.whtch.infra.inmemory;

import com.microsoft.whtch.domain.Watch;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InMemoryWatchRepositoryTest {

    @Test
    void shouldFindById() {
        InMemoryWatchRepository repository = new InMemoryWatchRepository();

        Watch rolex = repository.findById(1L);
        assertThat(rolex.getId()).isEqualTo(1L);
        assertThat(rolex.getName()).isEqualTo("Rolex");

        Watch michael = repository.findById(2L);
        assertThat(michael.getId()).isEqualTo(2L);
        assertThat(michael.getName()).isEqualTo("Michael");

        Watch swatch = repository.findById(3L);
        assertThat(swatch.getId()).isEqualTo(3L);
        assertThat(swatch.getName()).isEqualTo("Swatch");

        Watch casio = repository.findById(4L);
        assertThat(casio.getId()).isEqualTo(4L);
        assertThat(casio.getName()).isEqualTo("Casio");
    }

}