package com.project.user.service.impl;

import com.project.user.service.entities.Hotel;
import com.project.user.service.entities.Ratings;
import com.project.user.service.entities.User;
import com.project.user.service.exception.ResourceNotFoundException;
import com.project.user.service.externalServices.HotelService;
import com.project.user.service.repositories.UserRepo;
import com.project.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User user1= userRepo.save(user);
        return user1;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list=userRepo.findAll();
        list.forEach(i->{
            Ratings[] ratingArray= restTemplate.getForObject("http://RATING-SERVICE/ratings/getRatingByUserId/"+i.getUserId(), Ratings[].class);
            List<Ratings> ratingsList= Arrays.asList(ratingArray);
            ratingsList.forEach(ratings -> {
                Hotel forEntity = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+ratings.getHotelId(),Hotel.class);
                ratings.setHotel(forEntity);
            });
            i.setRating(ratingsList);
        });
        return list;
    }

    @Override
    public User getUser(String userId) {
        User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found with given id"+ "user id : " + userId));
        Ratings[] ratings =restTemplate.getForObject("http://RATING-SERVICE/ratings/getRatingByUserId/"+user.getUserId(), Ratings[].class);
        logger.info("{}",ratings);
        List<Ratings> ratingList = Arrays.asList(ratings);
        user.setRating(ratingList);

        List<Ratings> ratingOfUser= user.getRating().stream().map(i->{
            Hotel hotel= hotelService.getHotel(i.getHotelId());
            i.setHotel(hotel);
            return i;
        }).collect(Collectors.toList());


//        List<Ratings> ratingOfUser= user.getRating().stream().map(i->{
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+i.getHotelId(),Hotel.class);
//            Hotel hotel= forEntity.getBody();
//            logger.info("response status code: {}",forEntity.getStatusCode());
//            i.setHotel(hotel);
//            return i;
//        }).collect(Collectors.toList());

        return user;
    }
}
