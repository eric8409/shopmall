package com.eric.shopmall.dao.impl;

import com.eric.shopmall.constant.ProductCategory;
import com.eric.shopmall.dao.ProductDao;
import com.eric.shopmall.dto.ProductQueryParams;
import com.eric.shopmall.dto.ProductRequest;
import com.eric.shopmall.model.Product;
import com.eric.shopmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {

        String sql = "select count(*) from product WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {

        String sql =  "SELECT product_id, product_name, category, image_url, price, stock, description," +
                        "created_date, last_modified_date " +
                         "FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParams);


        //排序
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }


    @Override
    public Product getProductById(Integer product_Id) {


    String sql =  "SELECT product_id, product_name, category, image_url, price, stock, description," +
                    "created_date, last_modified_date " +
                     "FROM product WHERE product_id = :product_id";

    Map<String, Object> map = new HashMap<>();
    map.put("product_id", product_Id);


    List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

    if(productList.size()>0)
        return productList.get(0);
    else
        return null;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, " +
                     "description, created_date, last_modified_date) " +
                      "VALUES ( :product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date)";


        Map<String, Object> map = new HashMap<>();
        map.put("product_name", productRequest.getProduct_Name());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update( sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();
        return productId;
    }


    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {


        String sql = "UPDATE product SET product_name = :product_name, category = :category, image_url = :image_url, " +
                     "price = :price, stock = :stock, description = :description, last_modified_date = :last_modified_date" +
                     " WHERE product_id = :product_id";

        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productId);

        map.put("product_name", productRequest.getProduct_Name());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());


        map.put("last_modified_date", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }


    @Override
    public void deleteProductById(Integer productId) {

     String sql = "DELETE FROM product WHERE product_id = :product_id";

     Map<String, Object> map = new HashMap<>();
     map.put("product_id", productId);

     namedParameterJdbcTemplate.update(sql, map);
    }

    private  String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams){

        //查詢條件
        if( productQueryParams.getCategory() != null) {

            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }

        if(productQueryParams.getSearch() != null) {

            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        return  sql;
    }




}
