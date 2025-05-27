package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Dto.BestSellerProjection;
import com.ecommerce.Ecomerce.Entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    void deleteByOrderId(String orderId);
    @Query("""
        SELECT oi.product AS product, SUM(oi.quantity) AS totalQuantity
        FROM OrderItem oi
        WHERE oi.order.status.name = :statusName
        GROUP BY oi.product
        ORDER BY SUM(oi.quantity) DESC
    """)
    List<BestSellerProjection> findBestSellersByStatus(@Param("statusName") String statusName);
    @Query(value = """
        SELECT 
            oi.product_id      AS product,
            SUM(oi.quantity)   AS totalQuantity
        FROM order_items oi
        JOIN orders o
          ON oi.order_id = o.id
        JOIN order_statuses s
          ON o.order_status_id = s.id
        WHERE s.status_name = :statusName
          AND o.created_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
        GROUP BY oi.product_id
        ORDER BY SUM(oi.quantity) DESC
        """, nativeQuery = true)
    List<BestSellerProjection> findTrendingLast7Days(
            @Param("statusName") String statusName,
            Pageable pageable
    );
}