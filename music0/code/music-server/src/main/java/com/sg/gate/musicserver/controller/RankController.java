package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.gate.musicserver.domain.Rank;
import com.sg.gate.musicserver.service.RankService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addRank(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String songListId = request.getParameter("songListId");
        String consumerId = request.getParameter("consumerId");
        String score = request.getParameter("score");
        Rank rank = new Rank();
        List<Rank> ranks = rankService.selectByConsumerId(Integer.parseInt(consumerId));
        if (!ranks.isEmpty()) {
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"你已经评过分啦");
            return jsonObject;
        }
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        boolean flag = rankService.addRank(rank);
        if(flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object averageScore(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        return rankService.averageRank(Integer.parseInt(songListId));
    }
}
