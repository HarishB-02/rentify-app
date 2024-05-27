package com.managementsystem.poc.presidio_test.repository;

import com.managementsystem.poc.presidio_test.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {
    @Query(value = "select p from Property p where p.propertyId=:propertyId")
    public Property findByProperty(Integer propertyId);
    @Query(value = "select p from Property p where p.sellerId=:sellereName")
    List<Property> findBySellerId(Integer sellereName);
    @Query(value = "select p from Property p where p.propertyId=:propertyId and p.sellerId=:userId")
    Property findProperty(Integer propertyId, Integer userId);
}
