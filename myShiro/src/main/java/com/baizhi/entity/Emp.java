package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2018/9/13 0013.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String name;
    private String password;
    private String salt;
}
