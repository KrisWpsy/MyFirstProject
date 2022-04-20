package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {

    //    添加歌曲信息
    boolean add(Song song);

    //    更新歌曲信息
    boolean update(Song song);

    //    删除歌曲信息
    boolean delete(Integer id);

    //    查询歌曲信息根据歌曲id
    Song selectBySongId(Integer songId);

    //    查询歌曲信息根据歌手id
    List<Song> selectBySinger(Integer singerId);

    //    查询所有歌曲信息
    List<Song> selectAllSongs();

    //    根据歌曲名字查询
    List<Song> songOfName(String name);

//    根据关键词查询
    List<Song> likeSongOfName(String keyWords);

}
