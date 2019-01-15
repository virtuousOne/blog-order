package com.service;

import com.dao.UserRepository;
import com.util.MD5Utils;
import com.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 实现用户service接口
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //通过用户名和密码查找
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
