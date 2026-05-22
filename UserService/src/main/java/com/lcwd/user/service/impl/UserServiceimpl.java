package com.lcwd.user.service.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceimpl.class);

    @Override
    public User saveUserUser(User user) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        // GENERATE UNIQUE-ID
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        // IMPLIMENT REATING SERVICE CALL: USEING REST TAMPLETS
        return userRepository.findAll();
    }


    // Get Singale user
    @Override
    public User getUser(String userId) {
        // get user from database with the  help of your repositry
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not  not found on server !! : " + userId));
        // fetch rating of the above  from Rating Service
        // http://localhost:8083/ratings/users/7c1f7a24-1cac-4e7b-97b6-e036b3f0608a

        Rating[] ratingofUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info(" {} ",ratingofUser);
        List<Rating> ratings = Arrays.stream(ratingofUser).toList();



        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            //http://localhost:8082/hotels/5b620731-634d-4321-a03b-0a4539c34cf5
            // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("responce stutus code: {}",forEntity.getStatusCode());
            //set the hotel to rating

            rating.setHotel(hotel);
            //return the rating

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;

    }
}
