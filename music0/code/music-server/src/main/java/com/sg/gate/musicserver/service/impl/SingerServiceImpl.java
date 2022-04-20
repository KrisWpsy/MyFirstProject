package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.SingerMapper;
import com.sg.gate.musicserver.domain.Singer;
import com.sg.gate.musicserver.domain.SingerExample;
import com.sg.gate.musicserver.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean add(Singer singer) {
        return singerMapper.insert(singer) > 0;
    }

    @Override
    public boolean update(Singer singer) {
        return singerMapper.updateByPrimaryKeySelective(singer)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return singerMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean select(Integer id) {
        return singerMapper.selectByPrimaryKey(id) == null;
    }

    @Override
    public List<Singer> selectAllSingers() {
        List<Singer> singers = singerMapper.selectByExample(null);
        return singers;
    }

    @Override
    public List<Singer> singOfName(String name) {
        SingerExample singerExample = new SingerExample();
        singerExample.createCriteria().andNameLike(name);
        return singerMapper.selectByExample(singerExample);
    }

    @Override
    public List<Singer> selectBySex(Byte sex) {
        SingerExample singerExample = new SingerExample();
        singerExample.createCriteria().andSexEqualTo(sex);
        return singerMapper.selectByExample(singerExample);
    }
}
