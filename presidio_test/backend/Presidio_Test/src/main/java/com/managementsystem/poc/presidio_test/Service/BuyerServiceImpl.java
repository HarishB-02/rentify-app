package com.managementsystem.poc.presidio_test.Service;

import java.util.ArrayList;
import java.util.List;

import com.managementsystem.poc.presidio_test.model.LikeCount;
import com.managementsystem.poc.presidio_test.model.PropertyWrapper;
import com.managementsystem.poc.presidio_test.repository.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementsystem.poc.presidio_test.model.Property;
import com.managementsystem.poc.presidio_test.repository.PropertyRepo;
@Service
public class BuyerServiceImpl implements BuyerService{
	@Autowired
	PropertyRepo propertyRepo;
	@Autowired
	LikeRepo likeRepo;
	@Override
	public List<PropertyWrapper> getAllProperty() {
		List<Property> propertyList = propertyRepo.findAll();
		List<PropertyWrapper> propList = new ArrayList<PropertyWrapper>();
		for (Property property : propertyList) {
			PropertyWrapper prop = new PropertyWrapper();
			prop.setPropertyId(property.getPropertyId());
			prop.setSellerId(property.getSellerId());
			prop.setAddress(property.getAddress());
			prop.setBedrooms(property.getBedrooms());
			prop.setBathrooms(property.getBathrooms());
			prop.setNearbyLandmark(property.getNearbyLandmark());
			prop.setSqFt(property.getSqFt());
			prop.setPropertyRent(property.getPropertyRent());
			prop.setPropertyName(property.getPropertyName());
			LikeCount likeCount = getLikeCount(new LikeCount(0, 0, property.getPropertyId(), 0));
			prop.setLikes(likeCount.getLikeCount());
			propList.add(prop);
		}
		return propList;
	}
	@Override
	public LikeCount addLikeToProperty(LikeCount newLike) {
		LikeCount isLike=likeRepo.findLikedUser(newLike.getPropertyId(),newLike.getUserId());
		if(isLike!=null){
			likeRepo.delete(isLike);

		}
		else{
			likeRepo.save(newLike);

		}

		return newLike;
	}

	@Override
	public LikeCount getLikeCount(LikeCount likecount) {

		likecount.setLikeCount(likeRepo.findCount(likecount.getPropertyId()));
		return likecount;
	}

}
