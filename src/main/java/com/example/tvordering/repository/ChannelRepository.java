package com.example.tvordering.repository;

import com.example.tvordering.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("FROM Channel c where c.number = :number")
    Optional<Channel> findChannelByNumber(@Param("number") Integer number);

}
