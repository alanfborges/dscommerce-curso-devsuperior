package com.alandev.dscommerce.repositories;

import com.alandev.dscommerce.entities.OrdemItemPK;
import com.alandev.dscommerce.entities.Order;
import com.alandev.dscommerce.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrdemItemPK> {

}
