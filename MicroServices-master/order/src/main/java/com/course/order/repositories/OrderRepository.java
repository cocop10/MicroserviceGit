package com.course.order.repositories;


import com.course.order.domain.OrderDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDomain, Long> {
    @Query("select o from OrderDomain o where o.cartId = :cartId")
    List<OrderDomain> findByCartId(@Param("cartId") Long cartId);
}
