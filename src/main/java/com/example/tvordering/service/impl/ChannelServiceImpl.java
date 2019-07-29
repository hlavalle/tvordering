package com.example.tvordering.service.impl;

import com.example.tvordering.exceptions.TvorderingNotFoundException;
import com.example.tvordering.model.Channel;
import com.example.tvordering.repository.ChannelRepository;
import com.example.tvordering.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    @Override
    public Channel getChannelByNumber(Integer channelNumber) {
        return channelRepository.findChannelByNumber(channelNumber).orElseThrow(() -> new TvorderingNotFoundException("Channel "+channelNumber+" not found."));
    }
}
