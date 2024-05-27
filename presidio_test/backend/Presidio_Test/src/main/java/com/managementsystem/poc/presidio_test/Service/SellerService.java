package com.managementsystem.poc.presidio_test.Service;

import com.managementsystem.poc.presidio_test.model.Property;
import com.managementsystem.poc.presidio_test.model.PropertyWrapper;
import com.managementsystem.poc.presidio_test.model.User;

import java.util.List;

public interface SellerService {
    public Property getPropertyById(int id);

    String createProperty(Property property);

    String updateProperty(Property property);

    String deleteProperty(Integer propertyId,String email);

    List<PropertyWrapper> getAllProperty(String email);

    User getSellerById(Integer sellerId);
}
