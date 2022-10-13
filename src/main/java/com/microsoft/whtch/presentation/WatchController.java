package com.microsoft.whtch.presentation;

import com.microsoft.whtch.service.WatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WatchController {

    private final WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Long> buyWatch(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(watchService.getWatchPrice(ids), HttpStatus.OK);
    }
}
