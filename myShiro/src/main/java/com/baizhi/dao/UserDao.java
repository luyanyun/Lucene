package com.baizhi.dao;

import com.baizhi.entity.User;

/**
 * Created by Administrator on 2018/9/12 0012.
 */
public interface UserDao {
    User queryByid(String name);
}
