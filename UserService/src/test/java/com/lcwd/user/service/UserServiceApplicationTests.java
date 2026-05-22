package com.lcwd.user.service;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

    @Qualifier("com.lcwd.user.service.external.services.RatingService")
    @Autowired
    private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService retingservice;

//	@Test
//	void crateRating(){
//
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is using with feign client").build();
//		ResponseEntity<Rating> ratingResponseEntity = ratingService.createRating(rating);
//
//		//	Rating saveRating = ratingService.createRating(rating);  // CHANGE TO BE RESPONSE ENTITY WHEN I GET *STUTUS CODE SUR SOMTING MORE
//		System.out.println("new rating crated");
//
//	}
}
