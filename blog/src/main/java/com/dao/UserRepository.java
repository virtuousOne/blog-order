package com.dao;

import com.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 用户Dao
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface UserRepository extends JpaRepository<User,Long> {
  /*
  通过用户名和密码查找
   */
  User findByUsernameAndPassword(String username, String password);
}
