package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.Rank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankService {
//  查总分
    int selectScoreSum(Integer songListId);

//    增加评分
    boolean addRank(Rank rank);

//    计算歌单平均分
    int averageRank(Integer songListId);

//    查总评分人数
    int selectRankNum(Integer songListId);

    List<Rank> selectByConsumerId(Integer consumerId);
}
