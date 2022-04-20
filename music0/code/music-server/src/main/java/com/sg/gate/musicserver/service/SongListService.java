package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.SongList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongListService {

    //    添加歌单信息
    boolean add(SongList songList);

    //    更新歌单信息
    boolean update(SongList songList);

    //    删除歌单信息
    boolean delete(Integer id);

    //    查询所有歌单
    List<SongList> selectAllSongLists();

//    根据风格模糊查询
    List<SongList> likeStyle(String style);

//    根据标题包含的关键字查询
    List<SongList> likeTitle(String keyWords);

}
