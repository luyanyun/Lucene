package com.baizhi.service;

import com.baizhi.entity.Product;

import java.util.List;

/**
 * Created by Administrator on 2018/9/10 0010.
 */
public interface ProductService {
    void insert(Product product);
    public List<Product> query(String queryParam);
}
