package com.eric.shopmall.service.impl;

import com.eric.shopmall.dao.ProductDao;
import com.eric.shopmall.model.Product;
import com.eric.shopmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer product_Id) {

        return productDao.getProductById(product_Id);
    }
}
