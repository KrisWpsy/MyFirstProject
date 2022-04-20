package com.sg.gate.musicserver.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

//    验证密码是否正确
    boolean verifyPassword(String username,String password);
}
