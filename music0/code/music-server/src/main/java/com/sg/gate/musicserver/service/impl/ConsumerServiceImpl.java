package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.ConsumerMapper;
import com.sg.gate.musicserver.domain.Consumer;
import com.sg.gate.musicserver.domain.ConsumerExample;
import com.sg.gate.musicserver.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List<Consumer> selectAllConsumer() {
        return consumerMapper.selectByExample(null);
    }

    @Override
    public boolean addConsumer(Consumer consumer) {
        return consumerMapper.insertSelective(consumer) > 0;
    }

    @Override
    public boolean updateConsumer(Consumer consumer) {
        return consumerMapper.updateByPrimaryKeySelective(consumer) > 0;
    }

    @Override
    public boolean deleteConsumer(Integer id) {
        return consumerMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Consumer selectById(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean login(String username, String password) {
        ConsumerExample consumerExample = new ConsumerExample();
        consumerExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        return consumerMapper.selectByExample(consumerExample) != null;
    }

    @Override
    public List<Consumer> getByUsername(String username) {
        ConsumerExample consumerExample = new ConsumerExample();
        consumerExample.createCriteria().andUsernameEqualTo(username);
        return  consumerMapper.selectByExample(consumerExample);
    }

}
