package com.eric.shopmall.dao;

import com.eric.shopmall.dto.ProductRequest;
import com.eric.shopmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer product_Id);

    Integer createProduct(ProductRequest productRequest);
}
