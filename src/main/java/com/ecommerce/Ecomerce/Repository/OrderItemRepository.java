package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Dto.BestSellerProjection;
import com.ecommerce.Ecomerce.Entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
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
    @Query("""
        SELECT oi.product       AS product,
               SUM(oi.quantity) AS totalQuantity
        FROM   OrderItem oi
        WHERE  oi.order.status.name = :statusName
          AND  oi.order.createdAt >= :fromDate
        GROUP  BY oi.product
        ORDER  BY SUM(oi.quantity) DESC
    """)
    List<BestSellerProjection> findTrending(
            @Param("statusName") String statusName,
            @Param("fromDate") Date fromDate,
            Pageable             pageable
    );
}