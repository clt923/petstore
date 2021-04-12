package me.petstore.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import me.petstore.store.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from orders o where price > :#{#price}", nativeQuery = true)
    List<Order> getOrdersAbove25(@Param("price") double price);
}
