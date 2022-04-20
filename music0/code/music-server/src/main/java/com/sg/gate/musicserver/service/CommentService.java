package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<Comment> selectBySongList(Integer songListId);

    boolean deleteComment(Integer id);

    boolean insertComment(Comment comment);

    boolean likeComments(Comment comment);
}
