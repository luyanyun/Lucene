package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22 0022.
 */
public interface UserDao {
    public List<User> queryAll();
    public void  insert(User user);
    public void  update(User user);
    public void  delete(Integer id);
}
