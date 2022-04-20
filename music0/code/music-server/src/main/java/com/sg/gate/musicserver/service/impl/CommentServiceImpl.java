package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.CommentMapper;
import com.sg.gate.musicserver.domain.Comment;
import com.sg.gate.musicserver.domain.CommentExample;
import com.sg.gate.musicserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectBySongList(Integer songListId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andSongListIdEqualTo(songListId);
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public boolean deleteComment(Integer id) {
        return commentMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean insertComment(Comment comment) {
        return commentMapper.insertSelective(comment) > 0;
    }

    @Override
    public boolean likeComments(Comment comment) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andIdEqualTo(comment.getId());
        return commentMapper.updateByExampleSelective(comment,commentExample) > 0;
    }
}
