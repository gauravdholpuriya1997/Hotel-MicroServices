package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    // CREATE

    Rating create(Rating rating);

    //GET ALL RATINGS

    List<Rating> getRatings();


    //GET ALL BY UserID

    List<Rating> getRatingByUserId(String userId);


    //Get ALL BY HOTEL
    List<Rating> getRatingByHotelId(String hotelId);
}

