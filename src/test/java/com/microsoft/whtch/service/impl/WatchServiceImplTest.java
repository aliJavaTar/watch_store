package com.microsoft.whtch.service.impl;

import com.microsoft.whtch.service.WatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WatchServiceImplTest {

    @Autowired
    private WatchService watchService;

    @Test
    void calculateRolexPriceWithoutDiscount() {
        ArrayList<Long> rolexs = new ArrayList<>();

        rolexs.add(1L);
        rolexs.add(1L);

        assertThat(watchService.getWatchPrice(rolexs)).isEqualTo(200);
    }

    @Test
    void calculateRolexPriceWith_Discount() {
        ArrayList<Long> rolexs = new ArrayList<>();

        rolexs.add(1L);
        rolexs.add(1L);
        rolexs.add(1L);

        assertThat(watchService.getWatchPrice(rolexs)).isEqualTo(200);
    }


    @Test
    void calculateMichaelKorsPriceWithoutDiscount() {
        ArrayList<Long> michael = new ArrayList<>();


        michael.add(2L);

        assertThat(watchService.getWatchPrice(michael)).isEqualTo(80);
    }

    @Test
    void calculateMichaelKorsPriceWith_Discount() {
        ArrayList<Long> michael = new ArrayList<>();

        michael.add(2L);
        michael.add(2L);

        assertThat(watchService.getWatchPrice(michael)).isEqualTo(120);
    }

    @Test
    void calculateSwatchPriceWithoutDiscount() {
        ArrayList<Long> swatch = new ArrayList<>();

        swatch.add(3L);
        swatch.add(3L);
        swatch.add(3L);

        assertThat(watchService.getWatchPrice(swatch)).isEqualTo(150);
    }

    @Test
    void calculateCasioPriceWithoutDiscount() {
        ArrayList<Long> casio = new ArrayList<>();

        casio.add(4L);
        casio.add(4L);
        casio.add(4L);
        casio.add(4L);

        assertThat(watchService.getWatchPrice(casio)).isEqualTo(120);
    }

    @Test
    void calculateTest() {
        ArrayList<Long> watchs = new ArrayList<>();

        watchs.add(1L);
        watchs.add(1L);
        watchs.add(2L);
        watchs.add(3L);
        watchs.add(4L);
        //360
        assertThat(watchService.getWatchPrice(watchs)).isEqualTo(360);

    }

    @Test
    void calculateTestsho() {
        ArrayList<Long> watchs = new ArrayList<>();

        watchs.add(1L);
        watchs.add(1L);
        watchs.add(1L);
        watchs.add(2L);
        watchs.add(2L);
        watchs.add(2L);
        watchs.add(3L);
        watchs.add(4L);

        //480
        assertThat(watchService.getWatchPrice(watchs)).isEqualTo(480);

    }
}