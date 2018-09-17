package com.baizhi.service;

import com.baizhi.dao.ProductDaoImpl;
import com.baizhi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/9/10 0010.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDaoImpl productDao;
    @Override
    public void insert(Product product) {
        productDao.saveIndex(product);
    }
    @Override
    public List<Product> query(String queryParam){
        List<Product> products = productDao.queryByTerm(queryParam);
        return products;
    }
}
