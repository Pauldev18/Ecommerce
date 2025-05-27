package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Dto.BestSellerProjection;
import com.ecommerce.Ecomerce.Entity.Product;
import com.ecommerce.Ecomerce.Repository.OrderItemRepository;
import com.ecommerce.Ecomerce.Repository.ProductRepository;
import com.ecommerce.Ecomerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
    @Override
    public List<Product> getProductsByCategory(UUID categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }
    @Override
    public List<BestSellerProjection> getTrendingLast7Days(String statusName, int topN) {
        Pageable page = PageRequest.of(0, topN);
        return orderItemRepository.findTrendingLast7Days(statusName, page);
    }
    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        return productRepository.save(product);
    }
    @Override
    public List<BestSellerProjection> getBestSellers(String statusName) {
        return orderItemRepository.findBestSellersByStatus(statusName);
    }
    @Override
    public Product updateProduct(UUID id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setSlug(updatedProduct.getSlug());
        existing.setName(updatedProduct.getName());
        existing.setSku(updatedProduct.getSku());
        existing.setSalePrice(updatedProduct.getSalePrice());
        existing.setComparePrice(updatedProduct.getComparePrice());
        existing.setBuyingPrice(updatedProduct.getBuyingPrice());
        existing.setQuantity(updatedProduct.getQuantity());
        existing.setShortDescription(updatedProduct.getShortDescription());
        existing.setDescription(updatedProduct.getDescription());
        existing.setType(updatedProduct.getType());
        existing.setPublished(updatedProduct.isPublished());
        existing.setDisableOutOfStock(updatedProduct.isDisableOutOfStock());
        existing.setNote(updatedProduct.getNote());
        existing.setUpdatedAt(new Date());
        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
