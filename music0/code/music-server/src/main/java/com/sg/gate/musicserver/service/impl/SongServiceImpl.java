package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.SongMapper;
import com.sg.gate.musicserver.domain.Song;
import com.sg.gate.musicserver.domain.SongExample;
import com.sg.gate.musicserver.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    
    @Autowired
    private SongMapper songMapper;
    
    @Override
    public boolean add(Song song) {
        return songMapper.insert(song) > 0;
    }

    @Override
    public boolean update(Song song) {
        SongExample songExample = new SongExample();
        songExample.createCriteria().andIdEqualTo(song.getId());
        return songMapper.updateByExampleWithBLOBs(song,songExample) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return songMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Song selectBySongId(Integer songId) {
        return songMapper.selectByPrimaryKey(songId);
    }

    @Override
    public List<Song> selectBySinger(Integer singerId) {
        SongExample songExample = new SongExample();
        songExample.createCriteria().andSingerIdEqualTo(singerId);
//        这里一定要用这个查找方法，因为这个查找方法返回的resultMap中有lyric
        return songMapper.selectByExampleWithBLOBs(songExample);
    }

    @Override
    public List<Song> selectAllSongs() {
        return songMapper.selectByExample(null);
    }

    @Override
    public List<Song> songOfName(String name) {
        SongExample songExample = new SongExample();
        songExample.createCriteria().andNameEqualTo(name);
        return songMapper.selectByExample(songExample);
    }

    @Override
    public List<Song> likeSongOfName(String keyWords) {
        SongExample songExample = new SongExample();
        songExample.createCriteria().andNameLike(keyWords);
        return songMapper.selectByExampleWithBLOBs(songExample);
    }
}
