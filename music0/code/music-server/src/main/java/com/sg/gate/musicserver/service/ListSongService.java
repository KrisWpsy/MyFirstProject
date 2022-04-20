package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.ListSong;
import com.sg.gate.musicserver.domain.Song;
import com.sg.gate.musicserver.domain.SongList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListSongService {

    List<ListSong>  selectBySongListId(Integer songListId);

    boolean addSongForSongList(ListSong listSong);

    boolean deleteForSongList(Integer songId,Integer songListId);
}
