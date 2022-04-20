package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.sg.gate.musicserver.domain.Collect;
import com.sg.gate.musicserver.service.CollectService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String songId = request.getParameter("songId");
        boolean flag = collectService.deleteCollect(Integer.parseInt(userId), Integer.parseInt(songId));
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"删除失败");
        return jsonObject;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        if (songId == null) {
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"收藏歌曲为空");
            return jsonObject;
        }
        if (collectService.existSong(Integer.parseInt(songId),Integer.parseInt(userId))) {
            jsonObject.put(Constants.CODE,2);
            jsonObject.put(Constants.MSG,"歌曲已收藏");
            return jsonObject;
        }
//        String songListId = request.getParameter("songListId");
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(Boolean.getBoolean(type));
        collect.setSongId(Integer.parseInt(songId));
//        collect.setSongListId(Integer.parseInt(songListId));
        boolean flag = collectService.insertCollect(collect);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"收藏失败");
        return jsonObject;
    }

    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        return collectService.collectOfUserId(Integer.parseInt(userId));
    }
}
