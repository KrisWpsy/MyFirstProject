package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.SongListMapper;
import com.sg.gate.musicserver.domain.SongList;
import com.sg.gate.musicserver.domain.SongListExample;
import com.sg.gate.musicserver.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean add(SongList songList) {
        return songListMapper.insert(songList) > 0;
    }

    @Override
    public boolean update(SongList songList) {
        return songListMapper.updateByPrimaryKeySelective(songList) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return songListMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<SongList> selectAllSongLists() {
        return songListMapper.selectByExample(null);
    }

    @Override
    public List<SongList> likeStyle(String style) {
        SongListExample songListExample = new SongListExample();
        songListExample.createCriteria().andStyleLike(style);
        return songListMapper.selectByExample(songListExample);
    }

    @Override
    public List<SongList> likeTitle(String keyWords) {
        SongListExample songListExample = new SongListExample();
        songListExample.createCriteria().andTitleLike(keyWords);
        return songListMapper.selectByExample(songListExample);
    }
}
