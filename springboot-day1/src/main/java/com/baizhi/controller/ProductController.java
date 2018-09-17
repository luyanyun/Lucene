package com.baizhi.controller;

import com.baizhi.entity.Product;
import com.baizhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/10 0010.
 */
@Controller
@RequestMapping("/pro")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/pro")
    public void save(Product product){   //把数据添加到 索引库
        productService.insert(product);
    }
    @RequestMapping("/query")
    @ResponseBody
    public List<Product> query(String param){
        List<Product> query = productService.query(param);
        return query;
    }
}
