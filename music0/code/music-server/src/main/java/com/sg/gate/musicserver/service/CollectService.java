package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.Collect;
import com.sg.gate.musicserver.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectService {

    boolean deleteCollect(Integer userId,Integer songId);

    boolean insertCollect(Collect collect);

    List<Collect> collectOfUserId(Integer userId);

    boolean existSong(Integer songId,Integer userId);
}
