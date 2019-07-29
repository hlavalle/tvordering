package com.example.tvordering.controller;

import com.example.tvordering.model.Channel;
import com.example.tvordering.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping
    public ResponseEntity<List<Channel>> getAll() {

        List<Channel> channels = channelService.getAllChannels();

        return ResponseEntity.ok(channels);

    }

    @GetMapping("/{channelNumber}")
    public ResponseEntity<Channel> get(@PathVariable("channelNumber") Integer channelNumber) {

        Channel channel = channelService.getChannelByNumber(channelNumber);

        return ResponseEntity.ok(channel);

    }
}
