package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Dto.BestSellerProjection;
import com.ecommerce.Ecomerce.Dto.ProductDTO;
import com.ecommerce.Ecomerce.Entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(UUID id);
    Product createProduct(Product product);
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
    List<Product> getProductsByCategory(UUID categoryId);
    List<ProductDTO> getBestSellers(String statusName);
    List<ProductDTO> getTrendingLast7Days(String statusName, int topN);

}
