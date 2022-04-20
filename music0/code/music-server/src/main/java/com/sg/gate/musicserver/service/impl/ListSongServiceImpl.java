package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.ListSongMapper;
import com.sg.gate.musicserver.domain.*;
import com.sg.gate.musicserver.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> selectBySongListId(Integer songListId) {
        ListSongExample listSongExample = new ListSongExample();
        listSongExample.createCriteria().andSongListIdEqualTo(songListId);
        return listSongMapper.selectByExample(listSongExample);
    }

    @Override
    public boolean addSongForSongList(ListSong listSong) {
        return listSongMapper.insertSelective(listSong) > 0;
    }

    @Override
    public boolean deleteForSongList(Integer songId,Integer songListId) {
        ListSongExample listSongExample = new ListSongExample();
        listSongExample.createCriteria().andSongIdEqualTo(songId)
                .andSongListIdEqualTo(songListId);
        return listSongMapper.deleteByExample(listSongExample) > 0;
    }
}
