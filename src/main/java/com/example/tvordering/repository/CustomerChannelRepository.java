package com.example.tvordering.repository;

import com.example.tvordering.model.Channel;
import com.example.tvordering.model.CustomerChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerChannelRepository extends JpaRepository<CustomerChannel, CustomerChannel.Key> {

    @Query("SELECT cc.key.channel FROM CustomerChannel cc" +
            " WHERE cc.key.customer.id = :customerId" +
            " AND cc.subscribed = :subscribed")
    List<Channel> findUserChannelsBySubscribedStatus(
            @Param("customerId") Long customerId,
            @Param("subscribed") boolean subscribed);

    @Query("SELECT cc.key.channel FROM CustomerChannel cc" +
            " WHERE cc.key.customer.id = :customerId")
    List<Channel> findAllUserChannels(@Param("customerId") Long customerId);

}
