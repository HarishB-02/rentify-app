package com.managementsystem.poc.presidio_test.Service;

import java.util.List;

import com.managementsystem.poc.presidio_test.model.LikeCount;
import com.managementsystem.poc.presidio_test.model.Property;
import com.managementsystem.poc.presidio_test.model.PropertyWrapper;

public interface BuyerService {
	public List<PropertyWrapper> getAllProperty();

	public LikeCount addLikeToProperty(LikeCount newLike);

	public LikeCount getLikeCount(LikeCount likecount);

}
