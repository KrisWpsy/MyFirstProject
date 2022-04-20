package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.gate.musicserver.domain.SongList;
import com.sg.gate.musicserver.service.SongListService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object insertSongList(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title");
//        添加的歌单的默认头像
        String pic = "img/songListPic/123.jpg";
        String introduction = request.getParameter("introduction");
        String style = request.getParameter("style");
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.add(songList);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title");
        String pic = "img/songListPic/123.jpg";
        String introduction = request.getParameter("introduction");
        String style = request.getParameter("style");
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.add(songList);
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
    public Object deleteSongList(HttpServletRequest request) {
        String id = request.getParameter("id");
        return songListService.delete(Integer.parseInt(id));
    }

    @RequestMapping(value = "/allSongList",method = RequestMethod.GET)
    public Object selectAllSongList(HttpServletRequest request) {
        return songListService.selectAllSongLists();
    }

    @RequestMapping(value = "/updateSongListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("id")Integer id, @RequestParam("file")MultipartFile mpFile) {
        JSONObject jsonObject = new JSONObject();
        if (mpFile.isEmpty()) {
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"文件上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String pathName = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songListPic";
        File file = new File(pathName);
        if (!file.exists()) {
            file.mkdir();
        }
        String avtorPath = "/img/songListPic/" + fileName;
        File last = new File(pathName + System.getProperty("file.separator") + fileName);
        try {
            mpFile.transferTo(last);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(avtorPath);
            boolean flag = songListService.update(songList);
            if (flag) {
                jsonObject.put(Constants.CODE,1);
                jsonObject.put(Constants.MSG,"上传成功");
                return jsonObject;
            }
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    @RequestMapping(value = "/likeStyle",method = RequestMethod.GET)
    public Object likeStyle(HttpServletRequest request) {
        String style = request.getParameter("style");
        return songListService.likeStyle("%" + style + "%");
    }

    @RequestMapping(value = "/likeTitle",method = RequestMethod.GET)
    public Object likeTitle(HttpServletRequest request) {
        String keyWords = request.getParameter("keyWords");
        return songListService.likeStyle("%" + keyWords + "%");
    }
}
