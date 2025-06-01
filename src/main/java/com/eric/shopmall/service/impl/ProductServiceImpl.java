package com.eric.shopmall.service.impl;

import com.eric.shopmall.constant.ProductCategory;
import com.eric.shopmall.dao.ProductDao;
import com.eric.shopmall.dto.ProductRequest;
import com.eric.shopmall.model.Product;
import com.eric.shopmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductCategory category, String search) {

       return productDao.getProducts(category, search);
    }

    @Override
    public Product getProductById(Integer product_Id) {

        return productDao.getProductById(product_Id);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);
    }


    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

            productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {

        productDao.deleteProductById(productId);
    }


}
