package com.managementsystem.poc.presidio_test.controller;

import com.managementsystem.poc.presidio_test.Service.SellerService;
import com.managementsystem.poc.presidio_test.model.Property;
import com.managementsystem.poc.presidio_test.model.PropertyWrapper;
import com.managementsystem.poc.presidio_test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("seller/property")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @GetMapping("{sellerId}")
    public User getSellerDetails(@PathVariable("sellerId") Integer sellerId) {
        return sellerService.getSellerById(sellerId);
    }

    @GetMapping("getProperty/{propertyId}")
    public Property getProperty(@PathVariable("propertyId") Integer propertyId) {
        Property property=sellerService.getPropertyById(propertyId);
        return property;
    }

    @GetMapping("getProperty/all/{email}")
    public List<PropertyWrapper> getAllProperty(@PathVariable String email) {
        List<PropertyWrapper> property=sellerService.getAllProperty(email);
        return property;
    }
    @PostMapping("addProperty")
    public Map<String,String> addProperty(@RequestBody Property property) {
        Map<String,String> map=new HashMap<>();
        map.put("Status",sellerService.createProperty(property));
        return map;
    }
    @PutMapping("updateProperty")
    public Map<String,String> updateProperty(@RequestBody Property property) {
        Map<String,String> map=new HashMap<>();
        map.put("Status",sellerService.updateProperty(property));
        return map;
    }
    @DeleteMapping("deleteProperty/{propertyId}/{email}")
    public Map<String,String> deleteProperty(@PathVariable Integer propertyId, @PathVariable String email) {
        Map<String,String> map=new HashMap<>();
        map.put("Status",sellerService.deleteProperty(propertyId,email));
        return map;
    }
}
