package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class USerController {

    @Autowired
    private  UserService userService;

    private Logger logger= LoggerFactory.getLogger(USerController.class);

    // create
    @PostMapping
    public ResponseEntity<User> creatUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
    // Single user get

    int retryCount=1;

    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name ="ratingHotelService", fallbackMethod ="ratingHotelFallback")
    public ResponseEntity<User>getSingleUser(@PathVariable("userId") String userId){
        logger.info("Get Single User Handler: UserController");
        logger.info("Retry count:{}", retryCount);
        retryCount++;
        User user= userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    // creating Fall Bake method for circuitbreaker

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
      //  logger.info("Fallback is executed because service is down: {}", ex.getMessage());

        User dummyUser = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down")
                .userId("15534")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(dummyUser);
    }


    //ALL USER GET

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> allUser= userService.getAllUser();

        return ResponseEntity.ok(allUser);
    }

}


