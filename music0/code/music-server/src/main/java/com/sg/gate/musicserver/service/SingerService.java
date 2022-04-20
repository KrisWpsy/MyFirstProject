package com.sg.gate.musicserver.service;

import com.sg.gate.musicserver.domain.Singer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingerService {

//    添加歌手信息
    boolean add(Singer singer);

//    更新歌手信息
    boolean update(Singer singer);

//    删除歌手信息
    boolean delete(Integer id);

//    查询歌手信息根据id
    boolean select(Integer id);

//    查询所有歌手信息
    List<Singer> selectAllSingers();

//    根据歌手名字模糊查询
    List<Singer> singOfName(String name);

//    根据歌手性别查询
    List<Singer> selectBySex(Byte sex);
}
