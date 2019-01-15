package com.service;

import com.po.User;

/**
 * @Description: 用户service接口
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface UserService {

    User checkUser(String username, String password);//通过名字和密码查找
}
