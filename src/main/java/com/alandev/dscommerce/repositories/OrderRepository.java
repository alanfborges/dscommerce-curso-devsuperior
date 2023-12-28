package com.alandev.dscommerce.repositories;

import com.alandev.dscommerce.entities.Order;
import com.alandev.dscommerce.entities.User;
import com.alandev.dscommerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
