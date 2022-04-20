package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.gate.musicserver.domain.Comment;
import com.sg.gate.musicserver.service.CommentService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/commentOfSongListId",method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        return commentService.selectBySongList(Integer.parseInt(songListId));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        boolean flag = commentService.deleteComment(Integer.parseInt(id));
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
    public Object addComments(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content");
//        String up = request.getParameter("up");
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(Byte.parseByte(type));
        if(new Byte(type) == 0){
            comment.setSongId(Integer.parseInt(songId));
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
//        comment.setUp(Integer.parseInt(up));
        boolean flag = commentService.insertComment(comment);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Object likeComments(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String up = request.getParameter("up");
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        boolean flag = commentService.likeComments(comment);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"添加失败");
        return jsonObject;
    }
}
