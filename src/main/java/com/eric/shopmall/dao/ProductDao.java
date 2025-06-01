package com.eric.shopmall.dao;

import com.eric.shopmall.constant.ProductCategory;
import com.eric.shopmall.dto.ProductRequest;
import com.eric.shopmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer product_Id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
