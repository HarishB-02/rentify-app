package com.managementsystem.poc.presidio_test.controller;

import java.util.List;

import com.managementsystem.poc.presidio_test.model.LikeCount;
import com.managementsystem.poc.presidio_test.model.PropertyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.managementsystem.poc.presidio_test.Service.BuyerService;
import com.managementsystem.poc.presidio_test.model.Property;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("buyer")
public class BuyerController {
	@Autowired
	BuyerService buyerService;
	@GetMapping("allProperty")
	public List<PropertyWrapper> getAllProperty(){
		return buyerService.getAllProperty();
	}
	@PutMapping("like")
	public LikeCount addLike(@RequestBody LikeCount newLike) {
		return buyerService.getLikeCount(buyerService.addLikeToProperty(newLike));
	}
}

