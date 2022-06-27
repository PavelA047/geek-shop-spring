package com.example.orderservice.repositories;

import contract.entities.DeliveryAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long> {
     List<DeliveryAddress> findAllByUserId(Long id);
//    @Query("select d from DeliveryAddress d where d.user.id = :id")
//    List<DeliveryAddress> findAllByUserId(@Param("id") Long id);
}
