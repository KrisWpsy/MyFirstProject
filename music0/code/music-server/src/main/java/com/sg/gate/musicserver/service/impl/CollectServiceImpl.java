package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.CollectMapper;
import com.sg.gate.musicserver.domain.Collect;
import com.sg.gate.musicserver.domain.CollectExample;
import com.sg.gate.musicserver.domain.Song;
import com.sg.gate.musicserver.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean deleteCollect(Integer userId,Integer songId) {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andUserIdEqualTo(userId)
                .andSongIdEqualTo(songId);
        return collectMapper.deleteByExample(collectExample) > 0;
    }

    @Override
    public boolean insertCollect(Collect collect) {
        return collectMapper.insertSelective(collect) > 0;
    }

    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andUserIdEqualTo(userId);
        return collectMapper.selectByExample(collectExample);
    }

    @Override
    public boolean existSong(Integer songId, Integer userId) {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andSongIdEqualTo(songId)
                .andUserIdEqualTo(userId);
        return collectMapper.selectByExample(collectExample).size() > 0;
    }
}
