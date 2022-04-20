package com.sg.gate.musicserver.service.impl;

import com.sg.gate.musicserver.dao.AdminMapper;
import com.sg.gate.musicserver.domain.Admin;
import com.sg.gate.musicserver.domain.AdminExample;
import com.sg.gate.musicserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean verifyPassword(String username, String password) {
//        生成一个example对象
        AdminExample example = new AdminExample();
//        拼接sql语句，查询表
        example.createCriteria().andNameEqualTo(username).andPasswordEqualTo(password);
        List<Admin> list = adminMapper.selectByExample(example);
//        如果没查询到，list为空，即登录失败
        return !list.isEmpty();
    }
}
