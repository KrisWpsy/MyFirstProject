package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.Consumer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsumerService {

    List<Consumer> selectAllConsumer();

    boolean addConsumer(Consumer consumer);

    boolean updateConsumer(Consumer consumer);

    boolean deleteConsumer(Integer id);

    Consumer selectById(Integer id);

    boolean login(String username,String password);

    List<Consumer> getByUsername(String username);
}
