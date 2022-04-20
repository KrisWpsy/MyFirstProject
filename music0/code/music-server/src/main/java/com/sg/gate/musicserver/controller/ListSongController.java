package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.gate.musicserver.domain.ListSong;
import com.sg.gate.musicserver.service.ListSongService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.addSongForSongList(listSong);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        boolean flag = listSongService.deleteForSongList(Integer.parseInt(songId),Integer.parseInt(songListId));
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"删除失败");
        return jsonObject;
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object selectListSong(HttpServletRequest request) {
        return listSongService.selectBySongListId(Integer.parseInt(request.getParameter("songListId")));
    }
}
