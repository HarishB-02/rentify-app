package com.managementsystem.poc.presidio_test.Service;

import com.managementsystem.poc.presidio_test.model.Property;
import com.managementsystem.poc.presidio_test.model.PropertyWrapper;
import com.managementsystem.poc.presidio_test.model.User;
import com.managementsystem.poc.presidio_test.repository.PropertyRepo;
import com.managementsystem.poc.presidio_test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService{
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    UserRepo userRepo;

      @Override
    public Property getPropertyById(int propertyId) {
        Property property =propertyRepo.findByProperty(propertyId);
        return property;
    }

    @Override
    public String createProperty(Property property) {
          Integer sellerId=property.getSellerId();
          if(userRepo.findById(sellerId).get().isSeller()){
              Property newProperty=new Property();
              newProperty.setAddress(property.getAddress());
              newProperty.setSellerId(property.getSellerId());
              newProperty.setBathrooms(property.getBathrooms());
              newProperty.setBedrooms(property.getBedrooms());
              newProperty.setNearbyLandmark(property.getNearbyLandmark());
              newProperty.setPlace(property.getPlace());
              newProperty.setLikes(property.getLikes());
              newProperty.setPropertyType(property.getPropertyType());
              newProperty.setPropertyRent(property.getPropertyRent());
              newProperty.setPropertyName(property.getPropertyName());
              newProperty.setSqFt(property.getSqFt());
              propertyRepo.save(newProperty);
              return "Success";
          }
          else{
              return "Seller id is not seller";
          }
    }

    @Override
    public String updateProperty(Property property) {
        Property oldProperty=propertyRepo.findByProperty(property.getPropertyId());
        if(oldProperty==null){
            return "Property not found";
        }
        oldProperty.setAddress(property.getAddress());
        oldProperty.setSellerId(property.getSellerId());
        oldProperty.setBathrooms(property.getBathrooms());
        oldProperty.setBedrooms(property.getBedrooms());
        oldProperty.setNearbyLandmark(property.getNearbyLandmark());
        oldProperty.setPlace(property.getPlace());
        oldProperty.setLikes(property.getLikes());
        oldProperty.setPropertyRent(property.getPropertyRent());
        oldProperty.setPropertyName(property.getPropertyName());
        oldProperty.setPropertyType(property.getPropertyType());
        oldProperty.setSqFt(property.getSqFt());
        propertyRepo.save(oldProperty);
        return "Updated";
    }

    @Override
    public String deleteProperty(Integer propertyId,String email) {
         User user = userRepo.findByEmailId(email);
        if(user==null){
            return "User Not Found";
        }
        Property property=propertyRepo.findByProperty(propertyId);
         if(property==null){
             return "Property Not Found";
         }
         if(property.getSellerId()==user.getId()){
                 propertyRepo.delete(property);
                 return "deleted";
         }
        return "Cannot delete others property";
    }

    @Override
    public List<PropertyWrapper> getAllProperty(String email) {
          Integer id=userRepo.findByEmailId(email).getId();
          User user=userRepo.findByEmailId(email);
          if(user.isBuyer()){
              List<Property> property=propertyRepo.findAll();
              List<PropertyWrapper> propertyWrappers=new ArrayList<>();
              for(Property p:property){
                  PropertyWrapper propertyWrapper=new PropertyWrapper();
                  propertyWrapper.setAddress(p.getAddress());
                  propertyWrapper.setPropertyId(p.getPropertyId());
                  propertyWrapper.setBathrooms(p.getBathrooms());
                  propertyWrapper.setBedrooms(p.getBedrooms());
                  propertyWrapper.setNearbyLandmark(p.getNearbyLandmark());
                  propertyWrapper.setPlace(p.getPlace());
                  propertyWrapper.setSelleremail(email);
                  propertyWrapper.setLikes(p.getLikes());
                  propertyWrapper.setPropertyType(p.getPropertyType());
                  propertyWrapper.setPropertyRent(p.getPropertyRent());
                  propertyWrapper.setPropertyName(p.getPropertyName());
                  propertyWrapper.setSqFt(p.getSqFt());
                  propertyWrappers.add(propertyWrapper);
              }
              return propertyWrappers;
          }
          else {
              List<Property> property = propertyRepo.findBySellerId(id);
              List<PropertyWrapper> propertyWrappers = new ArrayList<>();
              for (Property p : property) {
                  PropertyWrapper propertyWrapper = new PropertyWrapper();
                  propertyWrapper.setAddress(p.getAddress());
                  propertyWrapper.setPropertyId(p.getPropertyId());
                  propertyWrapper.setBathrooms(p.getBathrooms());
                  propertyWrapper.setBedrooms(p.getBedrooms());
                  propertyWrapper.setNearbyLandmark(p.getNearbyLandmark());
                  propertyWrapper.setPlace(p.getPlace());
                  propertyWrapper.setSelleremail(email);
                  propertyWrapper.setLikes(p.getLikes());
                  propertyWrapper.setPropertyType(p.getPropertyType());
                  propertyWrapper.setPropertyRent(p.getPropertyRent());
                  propertyWrapper.setPropertyName(p.getPropertyName());
                  propertyWrapper.setSqFt(p.getSqFt());
                  propertyWrappers.add(propertyWrapper);
              }
              return propertyWrappers;
          }
    }

    @Override
    public User getSellerById(Integer sellerId) {
        return userRepo.findBySellerId(sellerId);
    }
}
