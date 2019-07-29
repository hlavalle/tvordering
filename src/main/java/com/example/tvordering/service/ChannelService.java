package com.example.tvordering.service;

import com.example.tvordering.model.Channel;

import java.util.List;

public interface ChannelService {

    List<Channel> getAllChannels();

    Channel getChannelByNumber(Integer channelNumber);

}
