package com.baizhi.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/10 0010.
 */
@Component
public class Product implements Serializable{
    private String id;
    private String  name;
    private String  desc;
    private Double  price;
    private String  img;
    private Integer status;
    private Date  productDate;

    public Product() {
    }

    public Product(String id, String name, String desc, Double price, String img, Integer status, Date productDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.img = img;
        this.status = status;
        this.productDate = productDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", productDate=" + productDate +
                '}';
    }
}
