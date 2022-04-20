package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.RankMapper;
import com.sg.gate.musicserver.domain.Rank;
import com.sg.gate.musicserver.domain.RankExample;
import com.sg.gate.musicserver.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;


    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    @Override
    public boolean addRank(Rank rank) {
        return rankMapper.insertSelective(rank) > 0;
    }

    @Override
    public int averageRank(Integer songListId) {
        int sumNum = selectRankNum(songListId);
        if (sumNum == 0) {
             return 0;
        }
        return selectScoreSum(songListId) / sumNum;
    }

    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    @Override
    public List<Rank> selectByConsumerId(Integer consumerId) {
        RankExample rankExample = new RankExample();
        rankExample.createCriteria().andConsumerIdEqualTo(consumerId);
        return rankMapper.selectByExample(rankExample);
    }

}
